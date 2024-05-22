package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.entity.JobHistory;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CompanyRepository;
import com.ulbs.careerstartup.repository.JobHistoryRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class JobHistoryService {

    private final CompanyRepository companyRepository;
    private JobHistoryRepository jobHistoryRepository;
    private Mapper mapper;

    private CompanyService companyService;

    private UserService userService;

    public Collection<JobHistoryDTO> findAllJobHistories() {
        return jobHistoryRepository.findAll().stream().map(mapper::jobHistoryToJobHistoryDTO).toList();
    }

    public JobHistoryDTO findJobHistoryById(UUID id) {
        return jobHistoryRepository.findById(id).map(mapper::jobHistoryToJobHistoryDTO).orElseThrow(() -> new EntityNotFoundException("JobHistory with id " + id + " not found"));
    }

    public Collection<JobHistoryDTO> findByCriteria(List<SearchCriteria> criteria) {
        return jobHistoryRepository.findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10)).map(mapper::jobHistoryToJobHistoryDTO).toList();
    }

    public JobHistoryDTO saveJobHistory(JobHistoryDTO jobHistoryDTO) throws MalformedURLException {
        if (jobHistoryDTO.getCompanyDTO().getId() == null) {
            jobHistoryDTO.setCompanyDTO(getCompanyInfo(jobHistoryDTO.getCompanyDTO().getWebsite()));
        }
        JobHistory jobHistory = mapper.jobHistoryDTOToJobHistory(jobHistoryDTO);
        jobHistory.setUser(mapper.userDTOToUser(jobHistoryDTO.getUserDTO()));
        return mapper.jobHistoryToJobHistoryDTO(jobHistoryRepository.save(jobHistory));
    }

    public CompanyDTO getCompanyInfo(String website) throws MalformedURLException {
        URL myUrl = new URL(website);
        String domain = myUrl.getHost();
        if (domain.startsWith("www.")) {
            domain = domain.substring(4);
        }
        return CompanyDTO.builder().name(domain.substring(0, 1).toUpperCase() + domain.substring(1, domain.lastIndexOf('.'))).website(website).build();
    }

    public JobHistoryDTO updateJobHistory(JobHistoryDTO jobHistoryDTO) throws MalformedURLException {
        if (jobHistoryDTO.getCompanyDTO().getId() == null) {
            jobHistoryDTO.setCompanyDTO(getCompanyInfo(jobHistoryDTO.getCompanyDTO().getWebsite()));
        }
        User user = mapper.userDTOToUser(jobHistoryDTO.getUserDTO());
        JobHistory jobHistory = mapper.jobHistoryDTOToJobHistory(jobHistoryDTO);
        jobHistory.setUser(user);
        return mapper.jobHistoryToJobHistoryDTO(jobHistoryRepository.save(jobHistory));
    }

    public void deleteJobHistory(UUID id) {
        jobHistoryRepository.deleteById(id);
    }
}
