package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.entity.Company;
import com.ulbs.careerstartup.entity.JobHistory;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.exception.InvalidURLException;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CompanyRepository;
import com.ulbs.careerstartup.repository.JobHistoryRepository;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class JobHistoryService {
    private final CompanyRepository companyRepository;
    private JobHistoryRepository jobHistoryRepository;
    private UserRepository userRepository;
    private Mapper mapper;

    public Collection<JobHistoryDTO> findAllJobHistories() {
        return jobHistoryRepository.findAll().stream().map(mapper::jobHistoryToJobHistoryDTO).toList();
    }

    public JobHistoryDTO findJobHistoryById(UUID id) {
        return jobHistoryRepository.findById(id).map(mapper::jobHistoryToJobHistoryDTO).orElseThrow(() -> new EntityNotFoundException("JobHistory with id " + id + " not found"));
    }

    public Collection<JobHistoryDTO> findByCriteria(List<SearchCriteria> criteria) {
        return jobHistoryRepository.findAll(new GenericSpecification<>(criteria)).stream().map(mapper::jobHistoryToJobHistoryDTO).toList();
    }

    public JobHistoryDTO saveJobHistory(JobHistoryDTO jobHistoryDTO) {
        JobHistory jobHistory = mapper.jobHistoryDTOToJobHistory(jobHistoryDTO);
        handleCompanyAssociation(jobHistoryDTO, jobHistory);
        handleUserAssociation(jobHistoryDTO, jobHistory);
        return mapper.jobHistoryToJobHistoryDTO(jobHistoryRepository.save(jobHistory));
    }

    public JobHistoryDTO updateJobHistory(JobHistoryDTO jobHistoryDTO) throws InvalidURLException {
        JobHistory jobHistory = jobHistoryRepository.findById(jobHistoryDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("JobHistory with id " + jobHistoryDTO.getId() + " does not exist"));

        handleCompanyAssociation(jobHistoryDTO, jobHistory);
        updateDetails(jobHistoryDTO, jobHistory);

        return mapper.jobHistoryToJobHistoryDTO(jobHistoryRepository.save(jobHistory));
    }

    public void deleteJobHistory(UUID id) {
        jobHistoryRepository.deleteById(id);
    }

    private void handleCompanyAssociation(JobHistoryDTO jobHistoryDTO, JobHistory jobHistory) throws InvalidURLException {
        CompanyDTO companyDTO = jobHistoryDTO.getCompanyDTO();
        if (companyDTO.getId() == null) {
            String website = companyDTO.getWebsite();
            companyRepository.findByWebsite(website)
                    .ifPresentOrElse(
                            jobHistory::setCompany,
                            () -> {
                                try {
                                    jobHistory.setCompany(companyRepository.save(getCompanyInfo(website)));
                                } catch (MalformedURLException e) {
                                    throw new InvalidURLException(e.getMessage(), e);
                                }
                            }
                    );
        } else {
            Company company = companyRepository.findById(companyDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyDTO.getId() + " was not found."));
            jobHistory.setCompany(company);
        }
    }

    private void handleUserAssociation(JobHistoryDTO jobHistoryDTO, JobHistory jobHistory) {
        UUID userId = jobHistoryDTO.getUserDTO().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " was not found."));
        jobHistory.setUser(user);
    }

    private void updateDetails(JobHistoryDTO jobHistoryDTO, JobHistory jobHistory) {
        if (jobHistoryDTO.getNeedQualification() != null) {
            jobHistory.setNeedQualification(jobHistoryDTO.getNeedQualification());
        }
        if (jobHistoryDTO.getDescription() != null) {
            jobHistory.setDescription(jobHistoryDTO.getDescription());
        }
        if (jobHistoryDTO.getPosition() != null) {
            jobHistory.setPosition(jobHistoryDTO.getPosition());
        }
        if (jobHistoryDTO.getStartDate() != null) {
            jobHistory.setStartDate(mapper.mapStringToTimestamp(jobHistoryDTO.getStartDate()));
        }
        if (jobHistoryDTO.getEndDate() != null) {
            jobHistory.setEndDate(mapper.mapStringToTimestamp(jobHistoryDTO.getEndDate()));
        }
    }

    private Company getCompanyInfo(String website) throws MalformedURLException {
        URL myUrl = new URL(website);
        String domain = myUrl.getHost();
        if (domain.startsWith("www.")) {
            domain = domain.substring(4);
        }
        return Company.builder().name(domain.substring(0, 1).toUpperCase() + domain.substring(1, domain.lastIndexOf('.'))).website(website).build();
    }

    public boolean isJobHistoryOwner(UUID id, Principal principal) {
        JobHistoryDTO jobHistoryDTO = findByCriteria(List.of(new SearchCriteria("id", "=", id),
                new SearchCriteria("user.email", "=", principal.getName()))).stream().toList().get(0);
        return jobHistoryDTO != null;
    }
}
