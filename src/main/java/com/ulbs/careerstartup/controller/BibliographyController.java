package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.BibliographyApiDoc;
import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.service.BibliographyService;
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
@RestController
@RequestMapping("/bibliographies")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Bibliography", description = "The Bibliography API")
public class BibliographyController implements BibliographyApiDoc {

    private BibliographyService bibliographyService;

    @GetMapping
    public Collection<BibliographyDTO> findAllBibliographies() {
        return bibliographyService.findAllBibliographies();
    }

    @GetMapping("/by-skills/{skillIds}")
    public Collection<BibliographyDTO> findBibliographiesBySkillIds(@PathVariable List<UUID> skillIds) {
        return bibliographyService.findBibliographiesBySkillIds(skillIds);
    }

    @PostMapping(BY_CRITERIA)
    public Collection<BibliographyDTO> findByCriteria(@RequestBody List<SearchCriteria> criteria) {
        return bibliographyService.findByCriteria(criteria);
    }

    @PostMapping
    public BibliographyDTO saveBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        return bibliographyService.saveBibliography(bibliographyDTO);
    }

    @PatchMapping
    public BibliographyDTO updateBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        return bibliographyService.updateBibliography(bibliographyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        bibliographyService.deleteBibliography(bibliographyDTO);
    }
}
