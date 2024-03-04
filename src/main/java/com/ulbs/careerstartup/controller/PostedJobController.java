package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.PostedJobApiDoc;
import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.service.PostedJobService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@Slf4j
@AllArgsConstructor
@RequestMapping("/messages")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "PostedJob", description = "The PostedJob API")
public class PostedJobController implements PostedJobApiDoc {

    private PostedJobService postedJobService;

    @GetMapping
    public ResponseEntity<Collection<PostedJobDTO>> findAllPostedJobs() {
        return ResponseEntity.ok(postedJobService.findAllPostedJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostedJobDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(postedJobService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<PostedJobDTO>> findPostedJobsByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(postedJobService.findPostedJobsByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<PostedJobDTO> savePostedJob(@RequestBody PostedJobDTO postedJobDTO) {
        return ResponseEntity.ok(postedJobService.savePostedJob(postedJobDTO));
    }

    @PatchMapping
    public ResponseEntity<PostedJobDTO> updatePostedJob(@RequestBody PostedJobDTO postedJobDTO) {
        return ResponseEntity.ok(postedJobService.updatePostedJob(postedJobDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostedJob(@RequestBody PostedJobDTO postedJobDTO) {
        postedJobService.deletePostedJob(postedJobDTO);
        return ResponseEntity.noContent().build();
    }
}
