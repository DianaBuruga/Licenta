package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.SkillApiDoc;
import com.ulbs.careerstartup.dto.SkillDTO;
import com.ulbs.careerstartup.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/skills")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
@Tag(name = "Skill", description = "The Skill API")
public class SkillController implements SkillApiDoc {

    private SkillService skillService;

    @GetMapping
    public Collection<SkillDTO> findAllSkills() {
        return skillService.findAllSkills();
    }

    @GetMapping("/{id}")
    public SkillDTO findSkillById(@PathVariable UUID id) {
        return skillService.findSkillById(id);
    }

    @PostMapping
    public SkillDTO saveSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.saveSkill(skillDTO);
    }

    @PatchMapping
    public SkillDTO updateSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.updateSkill(skillDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable UUID id) {
        skillService.deleteSkill(id);
    }
}
