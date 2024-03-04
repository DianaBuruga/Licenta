package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.JobHistoryApiDoc;
import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.service.JobHistoryService;
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
@RequestMapping("/jobshistory")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "JobHistory", description = "The JobHistory API")
public class JobHistoryController implements JobHistoryApiDoc {
    private JobHistoryService jobHistoryService;

    @GetMapping
    public ResponseEntity<Collection<JobHistoryDTO>> findAllJobHistories() {
        return ResponseEntity.ok(jobHistoryService.findAllJobHistories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobHistoryDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(jobHistoryService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public Collection<JobHistoryDTO> findJobHistoriesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return jobHistoryService.findJobHistoriesByCriteria(criteria);
    }

    @PostMapping
    public ResponseEntity<JobHistoryDTO> saveJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) {
        return ResponseEntity.ok(jobHistoryService.saveJobHistory(jobHistoryDTO));
    }

    @PatchMapping
    public ResponseEntity<JobHistoryDTO> updateJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) {
        return ResponseEntity.ok(jobHistoryService.updateJobHistory(jobHistoryDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) {
        jobHistoryService.deleteJobHistory(jobHistoryDTO);
        return ResponseEntity.noContent().build();
    }
}
