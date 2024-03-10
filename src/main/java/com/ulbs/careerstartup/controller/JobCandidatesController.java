package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.JobCandidatesApiDoc;
import com.ulbs.careerstartup.dto.JobCandidatesDTO;
import com.ulbs.careerstartup.entity.pk.JobCandidatesPK;
import com.ulbs.careerstartup.service.JobCandidatesService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@RestController
@AllArgsConstructor
@RequestMapping("/jobCandidates")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "JobCandidates", description = "The JobCandidates API")
public class JobCandidatesController implements JobCandidatesApiDoc {

    private JobCandidatesService jobCandidatesService;

    @GetMapping
    public Collection<JobCandidatesDTO> findAllJobCandidates() {
        return jobCandidatesService.findAllJobCandidates();
    }

    @GetMapping("/{id}/{jobId}")
    public JobCandidatesDTO findJobById(@PathVariable UUID jobId, @PathVariable UUID id) {
        return jobCandidatesService.findJobById(new JobCandidatesPK(jobId, id));
    }

    @GetMapping(BY_CRITERIA)
    public Collection<JobCandidatesDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return jobCandidatesService.findJobCandidatesByCriteria(criteria);
    }

    @PostMapping
    public JobCandidatesDTO saveJobCandidates(@RequestBody JobCandidatesDTO jobCandidatesDTO) {
        return jobCandidatesService.saveJobCandidates(jobCandidatesDTO);
    }

    @PatchMapping
    public JobCandidatesDTO updateJobCandidates(@RequestBody JobCandidatesDTO jobCandidatesDTO) {
        return jobCandidatesService.updateJobCandidates(jobCandidatesDTO);
    }

    @DeleteMapping
    public void deleteJobCandidates(@RequestBody JobCandidatesDTO jobCandidatesDTO) {
        jobCandidatesService.deleteJobCandidates(jobCandidatesDTO);
    }
}