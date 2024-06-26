package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.UserSkillApiDoc;
import com.ulbs.careerstartup.dto.UserSkillsDTO;
import com.ulbs.careerstartup.service.UserSkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/userSkills/")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
@Tag(name = "UserSkill", description = "The UserSkill API")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class UserSkillController implements UserSkillApiDoc {
    private UserSkillService userSkillService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public UserSkillsDTO saveUserSkill(@RequestBody UserSkillsDTO userSkillsDTO) {
            return userSkillService.saveUserSkill(userSkillsDTO);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public UserSkillsDTO updateUserSkill(@RequestBody UserSkillsDTO userSkillsDTO) {
        return userSkillService.updateUserSkill(userSkillsDTO);
    }

    @DeleteMapping("/{userId}/{skillId}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public void deleteUserSkill(@PathVariable UUID userId, @PathVariable UUID skillId) {
        userSkillService.deleteUserSkill(userId,skillId);
    }
}
