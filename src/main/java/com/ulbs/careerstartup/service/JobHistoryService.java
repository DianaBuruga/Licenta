package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.JobHistoryRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class JobHistoryService {

    private JobHistoryRepository jobHistoryRepository;
    private Mapper mapper;

    public Collection<JobHistoryDTO> findAllJobHistories() {
        return jobHistoryRepository
                .findAll()
                .stream()
                .map(mapper::jobHistoryToJobHistoryDTO)
                .toList();
    }

    public JobHistoryDTO findById(UUID id) {
        return jobHistoryRepository.findById(id)
                .map(mapper::jobHistoryToJobHistoryDTO)
                .orElseThrow(() -> new EntityNotFoundException("JobHistory with id " + id + " not found"));
    }

    public Collection<JobHistoryDTO> findJobHistoriesByCriteria(List<SearchCriteria> criteria) {
        return jobHistoryRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::jobHistoryToJobHistoryDTO)
                .toList();
    }

    public JobHistoryDTO saveJobHistory(JobHistoryDTO jobHistoryDTO) {
        return mapper.jobHistoryToJobHistoryDTO(jobHistoryRepository.save(mapper.jobHistoryDTOToJobHistory(jobHistoryDTO)));
    }

    public JobHistoryDTO updateJobHistory(JobHistoryDTO jobHistoryDTO) {
        return mapper.jobHistoryToJobHistoryDTO(jobHistoryRepository.save(mapper.jobHistoryDTOToJobHistory(jobHistoryDTO)));
    }

    public void deleteJobHistory(JobHistoryDTO jobHistoryDTO) {
        jobHistoryRepository.delete(mapper.jobHistoryDTOToJobHistory(jobHistoryDTO));
    }
}
