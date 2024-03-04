package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ExperienceApiDoc;
import com.ulbs.careerstartup.dto.ExperienceDTO;
import com.ulbs.careerstartup.service.ExperienceService;
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

@AllArgsConstructor
@RequestMapping("/experiences")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Experience", description = "The Experience API")
public class ExperienceController implements ExperienceApiDoc {

    private ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<Collection<ExperienceDTO>> findAllExperiences() {
        return ResponseEntity.ok(experienceService.findAllExperiences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(experienceService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<ExperienceDTO>> findExperiencesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(experienceService.findExperiencesByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> saveExperience(@RequestBody ExperienceDTO experienceDTO) {
        return ResponseEntity.ok(experienceService.saveExperience(experienceDTO));
    }

    @PatchMapping
    public ResponseEntity<ExperienceDTO> updateExperience(@RequestBody ExperienceDTO experienceDTO) {
        return ResponseEntity.ok(experienceService.updateExperience(experienceDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteExperience(@RequestBody ExperienceDTO experienceDTO) {
        experienceService.deleteExperience(experienceDTO);
        return ResponseEntity.noContent().build();
    }
}
