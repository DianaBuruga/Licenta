package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.SkillApiDoc;
import com.ulbs.careerstartup.dto.SkillDTO;
import com.ulbs.careerstartup.service.SkillService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/skills")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Skill", description = "The Skill API")
public class SkillController implements SkillApiDoc {

    private SkillService skillService;

    @GetMapping
    public ResponseEntity<Collection<SkillDTO>> findAllSkills() {
        return ResponseEntity.ok(skillService.findAllSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(skillService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<SkillDTO>> findSkillsByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(skillService.findSkillsByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<SkillDTO> saveSkill(@RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(skillService.saveSkill(skillDTO));
    }

    @PatchMapping
    public ResponseEntity<SkillDTO> updateSkill(@RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(skillService.updateSkill(skillDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSkill(@RequestBody SkillDTO skillDTO) {
        skillService.deleteSkill(skillDTO);
        return ResponseEntity.noContent().build();
    }
}
