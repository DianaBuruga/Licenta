package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.service.BibliographyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/bibliographies")
public class BibliographyController {
    private BibliographyService bibliographyService;

    @GetMapping
    public ResponseEntity<Collection<BibliographyDTO>> findAllBibliography() {
        return ResponseEntity.ok(bibliographyService.findAllBibliographies());
    }

    @GetMapping("/by-skills/{skillIds}")
    public Collection<BibliographyDTO> getBibliographiesBySkillIds(@PathVariable List<UUID> skillIds) {
        return bibliographyService.getBibliographiesBySkillIds(skillIds);
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
}
