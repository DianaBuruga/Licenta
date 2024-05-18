package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.SkillApiDoc;
import com.ulbs.careerstartup.dto.SkillDTO;
import com.ulbs.careerstartup.service.SkillService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
    public Collection<SkillDTO> findAllSkills() {
        return skillService.findAllSkills();
    }

    @GetMapping("/{id}")
    public SkillDTO findSkillById(@PathVariable UUID id) {
        return skillService.findSkillById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<SkillDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return skillService.findByCriteria(criteria);
    }

    @PostMapping
    public SkillDTO saveSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.saveSkill(skillDTO);
    }

    @PatchMapping
    public SkillDTO updateSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.updateSkill(skillDTO);
    }

    @DeleteMapping
    public void deleteSkill(@RequestBody SkillDTO skillDTO) {
        skillService.deleteSkill(skillDTO);
    }
}
