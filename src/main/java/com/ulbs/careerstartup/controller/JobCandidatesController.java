package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.JobCandidatesApiDoc;
import com.ulbs.careerstartup.dto.JobCandidatesDTO;
import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.entity.pk.JobCandidatesPK;
import com.ulbs.careerstartup.service.JobCandidatesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/jobCandidates")
@Tag(name = "JobCandidates", description = "The JobCandidates API")
public class JobCandidatesController implements JobCandidatesApiDoc {

    private JobCandidatesService jobCandidatesService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<JobCandidatesDTO> findAllJobCandidates() {
        return jobCandidatesService.findAllJobCandidates();
    }

    @GetMapping("/{id}/{jobId}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public JobCandidatesDTO findJobById(@PathVariable UUID jobId, @PathVariable UUID id) {
        return jobCandidatesService.findJobById(new JobCandidatesPK(jobId, id));
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'ADMIN')")
    public JobCandidatesDTO saveJobCandidates(@RequestBody PostedJobDTO postedJob, Principal principal) {
        return jobCandidatesService.saveJobCandidates(postedJob, principal);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('COMPANY_REPRESENTATIVE','ADMIN')")
    public JobCandidatesDTO updateJobCandidates(@RequestBody JobCandidatesDTO jobCandidatesDTO) {
        return jobCandidatesService.updateJobCandidates(jobCandidatesDTO);
    }

    @DeleteMapping("job/{jobId}/candidate/{candidateId}/")
    @PreAuthorize("hasAnyAuthority('COMPANY_REPRESENTATIVE','ADMIN')")
    public void deleteJobCandidates(@PathVariable UUID candidateId, UUID jobId) {
        jobCandidatesService.deleteJobCandidates(candidateId, jobId);
    }
}