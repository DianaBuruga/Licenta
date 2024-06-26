package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.entity.Company;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CompanyRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import com.ulbs.careerstartup.util.CompanyUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
        companyDTO.setLogoUrl(companyUtil.getFaviconUrl(companyDTO.getWebsite()));
        return mapper.companyToCompanyDTO(companyRepository.save(mapper.companyDTOToCompany(companyDTO)));
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyDTO.getId() + " not found"));
        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());
        company.setWebsite(companyDTO.getWebsite());
        company.setDescription(companyDTO.getDescription());
        return mapper.companyToCompanyDTO(companyRepository.save(company));
    }

    public String getFaviconUrl(UUID id) {
        return companyRepository.findById(id)
                .map(company -> companyUtil.getFaviconUrl(company.getWebsite()))
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found"));
    }

    @Transactional
    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }

    public Collection<CompanyDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return companyRepository
                .findAll(new GenericSpecification<>(searchCriteria))
                .stream()
                .map(mapper::companyToCompanyDTO)
                .toList();
    }

    public boolean isOwner(UUID id, Principal principal) {
        List<SearchCriteria> searchCriteria = List.of(new SearchCriteria("jobHistories.id", "=", id),
                new SearchCriteria("jobHistories.user.email", "=", principal.getName()),
                new SearchCriteria("jobHistories.user.role", "=", "COMPANY_REPRESENTATIVE"));
        CompanyDTO company = findByCriteria(searchCriteria).stream().toList().get(0);

        return company != null;
    }
}
