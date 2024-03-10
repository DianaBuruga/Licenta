package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.PostedJobApiDoc;
import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.service.PostedJobService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Collection<PostedJobDTO> findAllPostedJobs() {
        return postedJobService.findAllPostedJobs();
    }

    @GetMapping("/{id}")
    public PostedJobDTO findPostedJobById(@PathVariable UUID id) {
        return postedJobService.findPostedJobById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<PostedJobDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return postedJobService.findPostedJobsByCriteria(criteria);
    }

    @PostMapping
    public PostedJobDTO savePostedJob(@RequestBody PostedJobDTO postedJobDTO) {
        return postedJobService.savePostedJob(postedJobDTO);
    }

    @PatchMapping
    public PostedJobDTO updatePostedJob(@RequestBody PostedJobDTO postedJobDTO) {
        return postedJobService.updatePostedJob(postedJobDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePostedJob(@RequestBody PostedJobDTO postedJobDTO) {
        postedJobService.deletePostedJob(postedJobDTO);
    }
}
