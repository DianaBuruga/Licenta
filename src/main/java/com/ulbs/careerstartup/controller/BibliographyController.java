package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.BibliographyApiDoc;
import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.security.isOwnerRole.bibliography.IsBibliographyOwner;
import com.ulbs.careerstartup.service.BibliographyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/bibliographies")

@Tag(name = "Bibliography", description = "The Bibliography API")
public class BibliographyController implements BibliographyApiDoc {

    private BibliographyService bibliographyService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<BibliographyDTO> findAllBibliographies() {
        return bibliographyService.findAllBibliographies();
    }

    @GetMapping("/by-skills/{skillIds}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<BibliographyDTO> findBibliographiesBySkillIds(@PathVariable List<UUID> skillIds) {
        return bibliographyService.findBibliographiesBySkillIds(skillIds);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public BibliographyDTO saveBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        return bibliographyService.saveBibliography(bibliographyDTO);
    }

    @PatchMapping
    @IsBibliographyOwner
    @PreAuthorize("hasAnyAuthority('TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public BibliographyDTO updateBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        return bibliographyService.updateBibliography(bibliographyDTO);
    }

    @DeleteMapping("/{id}")
    @IsBibliographyOwner
    @PreAuthorize("hasAnyAuthority('TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public void deleteBibliography(@PathVariable UUID id) {
        bibliographyService.deleteBibliography(id);
    }
}
