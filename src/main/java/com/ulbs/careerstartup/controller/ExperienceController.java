package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ExperienceApiDoc;
import com.ulbs.careerstartup.dto.ExperienceDTO;
import com.ulbs.careerstartup.service.ExperienceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/experiences")
//@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Experience", description = "The Experience API")
@Validated
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

    @PostMapping
    public ExperienceDTO saveExperience(@RequestBody ExperienceDTO experienceDTO) {
        return experienceService.saveExperience(experienceDTO);
    }

    @PatchMapping
    public ExperienceDTO updateExperience(@RequestBody ExperienceDTO experienceDTO) {
        return experienceService.updateExperience(experienceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExperience(@PathVariable UUID id) {
        experienceService.deleteExperience(id);
    }
}
