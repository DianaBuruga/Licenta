package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CompanyRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import com.ulbs.careerstartup.util.CompanyUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {
    private CompanyRepository companyRepository;

    private CompanyUtil companyUtil;
    private Mapper mapper;

    public @NotNull Collection<CompanyDTO> findAllCompanies() {
        return companyRepository.findAll().stream().map(mapper::companyToCompanyDTO).toList();
    }

    public CompanyDTO findCompanyById(UUID id) {
        return companyRepository.findById(id)
                .map(mapper::companyToCompanyDTO)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found"));
    }

    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        return mapper.companyToCompanyDTO(companyRepository.save(mapper.companyDTOToCompany(companyDTO)));
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        if (companyRepository.existsById(companyDTO.getId()))
            return mapper.companyToCompanyDTO(companyRepository.save(mapper.companyDTOToCompany(companyDTO)));
        else
            throw new EntityNotFoundException("Company with id " + companyDTO.getId() + " not found");
    }

    public String getFaviconUrl(UUID id) {
        return companyRepository.findById(id)
                .map(company -> companyUtil.getFaviconUrl(company.getWebsite()))
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found"));
    }

    @Transactional
    public void deleteCompany(CompanyDTO companyDTO) {
        companyRepository.delete(mapper.companyDTOToCompany(companyDTO));
    }

    public Collection<CompanyDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return companyRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::companyToCompanyDTO)
                .toList();
    }
}
