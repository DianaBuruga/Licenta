package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.SpecializationApiDoc;
import com.ulbs.careerstartup.dto.SpecializationDTO;
import com.ulbs.careerstartup.service.SpecializationService;
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

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/specializations")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Specialization", description = "The Specialization API")
public class SpecializationController implements SpecializationApiDoc {
    private SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<Collection<SpecializationDTO>> findAllSpecializations() {
        return ResponseEntity.ok(specializationService.findAllSpecializations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(specializationService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<SpecializationDTO>> findSpecializationsByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(specializationService.findSpecializationsByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<SpecializationDTO> saveSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        return ResponseEntity.ok(specializationService.saveSpecialization(specializationDTO));
    }

    @PatchMapping
    public ResponseEntity<SpecializationDTO> updateSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        return ResponseEntity.ok(specializationService.updateSpecialization(specializationDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSpecialization(@RequestBody SpecializationDTO specializationDTO) {
        specializationService.deleteSpecialization(specializationDTO);
        return ResponseEntity.noContent().build();
    }
}
