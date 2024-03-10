package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ExperienceApiDoc;
import com.ulbs.careerstartup.dto.ExperienceDTO;
import com.ulbs.careerstartup.service.ExperienceService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
    public Collection<ExperienceDTO> findAllExperiences() {
        return experienceService.findAllExperiences();
    }

    @GetMapping("/{id}")
    public ExperienceDTO findExperienceById(@PathVariable UUID id) {
        return experienceService.findExperienceById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<ExperienceDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return experienceService.findExperiencesByCriteria(criteria);
    }

    @PostMapping
    public ExperienceDTO saveExperience(@RequestBody ExperienceDTO experienceDTO) {
        return experienceService.saveExperience(experienceDTO);
    }

    @PatchMapping
    public ExperienceDTO updateExperience(@RequestBody ExperienceDTO experienceDTO) {
        return experienceService.updateExperience(experienceDTO);
    }

    @DeleteMapping
    public void deleteExperience(@RequestBody ExperienceDTO experienceDTO) {
        experienceService.deleteExperience(experienceDTO);
    }
}
