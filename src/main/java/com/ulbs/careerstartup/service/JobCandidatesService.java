package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.api.model.ApplicationEmailRequest;
import com.ulbs.careerstartup.dto.JobCandidatesDTO;
import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.entity.JobCandidates;
import com.ulbs.careerstartup.entity.PostedJob;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.entity.pk.JobCandidatesPK;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.JobCandidatesRepository;
import com.ulbs.careerstartup.repository.PostedJobRepository;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.NOT_FOUND;

@Service
@AllArgsConstructor
public class JobCandidatesService {

    private final PostedJobRepository postedJobRepository;
    private final UserRepository userRepository;
    private JobCandidatesRepository jobCandidatesRepository;
    private EmailService emailService;
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

    public JobCandidatesDTO saveJobCandidates(PostedJobDTO postedJobDTO, Principal principal) {
        PostedJob postedJob = postedJobRepository.findById(postedJobDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("PostedJob with id " + postedJobDTO.getId() + NOT_FOUND));
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User with email" + NOT_FOUND));
        JobCandidates jobCandidates = JobCandidates.builder()
                .id(new JobCandidatesPK(user.getId(), postedJob.getId()))
                .postedJob(postedJob)
                .user(user)
                .applicationDate(Timestamp.from(Instant.now()))
                .build();
        jobCandidates = jobCandidatesRepository.save(jobCandidates);
        sendConfirmationEmail(user, postedJob);
        return mapper.jobCandidatesToJobCandidatesDTO(jobCandidates);
    }

    public JobCandidatesDTO updateJobCandidates(JobCandidatesDTO jobCandidatesDTO) {
        if (jobCandidatesRepository.existsById(new JobCandidatesPK(jobCandidatesDTO.getCandidateId(), jobCandidatesDTO.getJobId())))
            return mapper.jobCandidatesToJobCandidatesDTO(jobCandidatesRepository.save(mapper.jobCandidatesDTOToJobCandidates(jobCandidatesDTO)));
        else throw new EntityNotFoundException("JobCandidates with id " + jobCandidatesDTO.getJobId() + NOT_FOUND);
    }

    @Transactional
    public void deleteJobCandidates(UUID candidateId, UUID jobId) {
        jobCandidatesRepository.deleteById(new JobCandidatesPK(candidateId, jobId));
    }

    public Collection<JobCandidatesDTO> findJobCandidatesByCriteria(List<SearchCriteria> criteria) {
        return jobCandidatesRepository
                .findAll(new GenericSpecification<>(criteria))
                .stream()
                .map(mapper::jobCandidatesToJobCandidatesDTO)
                .toList();
    }

    private void sendConfirmationEmail(User user, PostedJob postedJob) {
        String message = "You have applied for the " + postedJob.getPosition() + " at " + postedJob.getCompany().getName() + ".";
        String subject = "Application for job " + postedJob.getPosition() + " at company " + postedJob.getCompany().getName();
        ApplicationEmailRequest emailRequest =
                new ApplicationEmailRequest(user.getEmail(), subject, user.getName(), message, mapper.mapTimestampToString(postedJob.getOpenUntil()));
        emailService.sendConfirmationApplication(emailRequest);
    }
}
