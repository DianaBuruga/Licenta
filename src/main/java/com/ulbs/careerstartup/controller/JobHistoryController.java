package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.JobHistoryApiDoc;
import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.service.JobHistoryService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.*;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@RestController
@AllArgsConstructor
@RequestMapping("/jobs/history")
//@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "JobHistory", description = "The JobHistory API")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
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

    @PostMapping(BY_CRITERIA)
    public Collection<JobHistoryDTO> findByCriteria(@RequestBody List<SearchCriteria> criteria) {
        return jobHistoryService.findByCriteria(criteria);
    }

    @PostMapping
    public JobHistoryDTO saveJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) throws MalformedURLException {
        return jobHistoryService.saveJobHistory(jobHistoryDTO);
    }

    @PatchMapping
    public JobHistoryDTO updateJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) throws MalformedURLException {
        return jobHistoryService.updateJobHistory(jobHistoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteJobHistory(@PathVariable UUID id) {
        jobHistoryService.deleteJobHistory(id);
    }
}
