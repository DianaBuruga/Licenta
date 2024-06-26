package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.PostedJobApiDoc;
import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.security.isOwnerRole.postedJob.IsPostedJobOwner;
import com.ulbs.careerstartup.service.PostedJobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/company/jobs")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
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

    @PostMapping
    public PostedJobDTO savePostedJob(@RequestBody PostedJobDTO postedJobDTO, Principal principal) {
        return postedJobService.savePostedJob(postedJobDTO, principal);
    }

    @PatchMapping
    @IsPostedJobOwner
    public PostedJobDTO updatePostedJob(@RequestBody PostedJobDTO postedJobDTO) {
        return postedJobService.updatePostedJob(postedJobDTO);
    }

    @DeleteMapping("/{id}")
    @IsPostedJobOwner
    public void deletePostedJob(@PathVariable UUID id) {
        postedJobService.deletePostedJob(id);
    }
}
