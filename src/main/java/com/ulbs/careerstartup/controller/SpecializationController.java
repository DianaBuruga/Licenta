package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.SpecializationApiDoc;
import com.ulbs.careerstartup.dto.SpecializationDTO;
import com.ulbs.careerstartup.service.SpecializationService;
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

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/specializations")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Specialization", description = "The Specialization API")
public class SpecializationController implements SpecializationApiDoc {
    private SpecializationService specializationService;

    @GetMapping
    public Collection<SpecializationDTO> findAllSpecializations() {
        return specializationService.findAllSpecializations();
    }

    @GetMapping("/{id}")
    public SpecializationDTO findSpecializationById(@PathVariable UUID id) {
        return specializationService.findSpecializationById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<SpecializationDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return specializationService.findSpecializationsByCriteria(criteria);
    }

    @PostMapping
    public SpecializationDTO saveSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        return specializationService.saveSpecialization(specializationDTO);
    }

    @PatchMapping
    public SpecializationDTO updateSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        return specializationService.updateSpecialization(specializationDTO);
    }

    @DeleteMapping
    public void deleteSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        specializationService.deleteSpecialization(specializationDTO);
    }
}
