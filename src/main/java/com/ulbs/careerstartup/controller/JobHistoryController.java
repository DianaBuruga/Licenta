package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.JobHistoryApiDoc;
import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.service.JobHistoryService;
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
@RequestMapping("/jobs/history")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "JobHistory", description = "The JobHistory API")
public class JobHistoryController implements JobHistoryApiDoc {
    private JobHistoryService jobHistoryService;

    @GetMapping
    public Collection<JobHistoryDTO> findAllJobHistories() {
        return jobHistoryService.findAllJobHistories();
    }

    @GetMapping("/{id}")
    public JobHistoryDTO findJobHistoryById(@PathVariable UUID id) {
        return jobHistoryService.findJobHistoryById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<JobHistoryDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return jobHistoryService.findJobHistoriesByCriteria(criteria);
    }

    @PostMapping
    public JobHistoryDTO saveJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) {
        return jobHistoryService.saveJobHistory(jobHistoryDTO);
    }

    @PatchMapping
    public JobHistoryDTO updateJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) {
        return jobHistoryService.updateJobHistory(jobHistoryDTO);
    }

    @DeleteMapping
    public void deleteJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) {
        jobHistoryService.deleteJobHistory(jobHistoryDTO);
    }
}
