package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.SpecializationApiDoc;
import com.ulbs.careerstartup.dto.SpecializationDTO;
import com.ulbs.careerstartup.security.isOwnerRole.specialization.IsSpecializationOwner;
import com.ulbs.careerstartup.service.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/specializations")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER','ADMIN')")
@Tag(name = "Specialization", description = "The Specialization API")
public class SpecializationController implements SpecializationApiDoc {
    private SpecializationService specializationService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<SpecializationDTO> findAllSpecializations() {
        return specializationService.findAllSpecializations();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public SpecializationDTO findSpecializationById(@PathVariable UUID id) {
        return specializationService.findSpecializationById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER','ADMIN')")
    public SpecializationDTO saveSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        return specializationService.saveSpecialization(specializationDTO);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER','ADMIN')")
    @IsSpecializationOwner
    public SpecializationDTO updateSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        return specializationService.updateSpecialization(specializationDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER','ADMIN')")
    @IsSpecializationOwner
    public void deleteSpecialization(@PathVariable UUID id) {
        specializationService.deleteSpecialization(id);
    }
}
