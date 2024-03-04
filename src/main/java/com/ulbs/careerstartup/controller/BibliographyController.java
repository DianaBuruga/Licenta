package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.BibliographyApiDoc;
import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.service.BibliographyService;
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
@RestController
@RequestMapping("/bibliographies")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Bibliography", description = "The Bibliography API")
public class BibliographyController implements BibliographyApiDoc {

    private BibliographyService bibliographyService;

    @GetMapping
    public ResponseEntity<Collection<BibliographyDTO>> findAllBibliography() {
        return ResponseEntity.ok(bibliographyService.findAllBibliographies());
    }

    @GetMapping("/by-skills/{skillIds}")
    public Collection<BibliographyDTO> findBibliographiesBySkillIds(@PathVariable List<UUID> skillIds) {
        return bibliographyService.findBibliographiesBySkillIds(skillIds);
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<BibliographyDTO>> findBibliographiesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(bibliographyService.findBibliographiesByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<BibliographyDTO> saveBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        BibliographyDTO savedBibliographyDTO = bibliographyService.saveBibliography(bibliographyDTO);
        return ResponseEntity.ok(savedBibliographyDTO);
    }

    @PatchMapping
    public ResponseEntity<BibliographyDTO> updateBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        BibliographyDTO updatedBibliographyDTO = bibliographyService.updateBibliography(bibliographyDTO);
        return ResponseEntity.ok(updatedBibliographyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBibliography(@RequestBody BibliographyDTO bibliographyDTO) {
        bibliographyService.deleteBibliography(bibliographyDTO);
        return ResponseEntity.noContent().build();
    }
}
