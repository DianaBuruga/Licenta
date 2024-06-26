package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.JobHistoryApiDoc;
import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.service.JobHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/jobs/history")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
@Tag(name = "JobHistory", description = "The JobHistory API")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class JobHistoryController implements JobHistoryApiDoc {
    private JobHistoryService jobHistoryService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<JobHistoryDTO> findAllJobHistories() {
        return jobHistoryService.findAllJobHistories();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public JobHistoryDTO findJobHistoryById(@PathVariable UUID id) {
        return jobHistoryService.findJobHistoryById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public JobHistoryDTO saveJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) throws MalformedURLException {
        return jobHistoryService.saveJobHistory(jobHistoryDTO);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public JobHistoryDTO updateJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) throws MalformedURLException {
        return jobHistoryService.updateJobHistory(jobHistoryDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public void deleteJobHistory(@PathVariable UUID id) {
        jobHistoryService.deleteJobHistory(id);
    }
}
