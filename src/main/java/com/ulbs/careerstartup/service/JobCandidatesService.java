package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.JobCandidatesDTO;
import com.ulbs.careerstartup.entity.pk.JobCandidatesPK;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.JobCandidatesRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.ulbs.careerstartup.constant.Constants.NOT_FOUND;

@Service
@AllArgsConstructor
public class JobCandidatesService {

    private JobCandidatesRepository jobCandidatesRepository;
    private Mapper mapper;

    public Collection<JobCandidatesDTO> findAllJobCandidates() {
        return jobCandidatesRepository.findAll().stream().map(mapper::jobCandidatesToJobCandidatesDTO).toList();
    }

    public JobCandidatesDTO findJobById(JobCandidatesPK id) {
        return jobCandidatesRepository.findById(id).map(mapper::jobCandidatesToJobCandidatesDTO).orElseThrow(() -> new EntityNotFoundException("JobCandidates with id " + id + NOT_FOUND));
    }

    public Collection<JobCandidatesDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return jobCandidatesRepository.findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10)).map(mapper::jobCandidatesToJobCandidatesDTO).toList();
    }

    public JobCandidatesDTO saveJobCandidates(JobCandidatesDTO jobCandidatesDTO) {
        return mapper.jobCandidatesToJobCandidatesDTO(jobCandidatesRepository.save(mapper.jobCandidatesDTOToJobCandidates(jobCandidatesDTO)));
    }

    public JobCandidatesDTO updateJobCandidates(JobCandidatesDTO jobCandidatesDTO) {
        if (jobCandidatesRepository.existsById(new JobCandidatesPK(jobCandidatesDTO.getCandidateId(), jobCandidatesDTO.getJobId())))
            return mapper.jobCandidatesToJobCandidatesDTO(jobCandidatesRepository.save(mapper.jobCandidatesDTOToJobCandidates(jobCandidatesDTO)));
        else throw new EntityNotFoundException("JobCandidates with id " + jobCandidatesDTO.getJobId() + NOT_FOUND);
    }

    @Transactional
    public void deleteJobCandidates(JobCandidatesDTO jobCandidatesDTO) {
        JobCandidatesPK id = new JobCandidatesPK(jobCandidatesDTO.getCandidateId(), jobCandidatesDTO.getJobId());
        if (jobCandidatesRepository.existsById(id)) {
            jobCandidatesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("JobCandidates with id " + id.getJobId() + NOT_FOUND);
        }
    }

    public Collection<JobCandidatesDTO> findJobCandidatesByCriteria(List<SearchCriteria> criteria) {
        return jobCandidatesRepository
                .findAll(new GenericSpecification<>(criteria))
                .stream()
                .map(mapper::jobCandidatesToJobCandidatesDTO)
                .toList();
    }
}
