package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.JobCandidatesApiDoc;
import com.ulbs.careerstartup.dto.JobCandidatesDTO;
import com.ulbs.careerstartup.entity.pk.JobCandidatesPK;
import com.ulbs.careerstartup.service.JobCandidatesService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Collection<JobCandidatesDTO>> findAllJobCandidates() {
        return ResponseEntity.ok(jobCandidatesService.findAllJobCandidates());
    }

    @GetMapping("/{id}/{jobId}")
    public ResponseEntity<JobCandidatesDTO> findById(@PathVariable UUID jobId, @PathVariable UUID id) {
        return ResponseEntity.ok(jobCandidatesService.findById(new JobCandidatesPK(jobId, id)));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<JobCandidatesDTO>> findJobCandidatesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(jobCandidatesService.findJobCandidatesByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<JobCandidatesDTO> saveJobCandidates(@RequestBody JobCandidatesDTO jobCandidatesDTO) {
        return ResponseEntity.ok(jobCandidatesService.saveJobCandidates(jobCandidatesDTO));
    }

    @PatchMapping
    public ResponseEntity<JobCandidatesDTO> updateJobCandidates(@RequestBody JobCandidatesDTO jobCandidatesDTO) {
        return ResponseEntity.ok(jobCandidatesService.updateJobCandidates(jobCandidatesDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteJobCandidates(@RequestBody JobCandidatesDTO jobCandidatesDTO) {
        jobCandidatesService.deleteJobCandidates(jobCandidatesDTO);
        return ResponseEntity.noContent().build();
    }
}