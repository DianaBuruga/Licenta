package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.UserSkillApiDoc;
import com.ulbs.careerstartup.dto.UserSkillsDTO;
import com.ulbs.careerstartup.service.UserSkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/userSkills/")
//@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "UserSkill", description = "The UserSkill API")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class UserSkillController implements UserSkillApiDoc {
    private UserSkillService userSkillService;

    @PostMapping
    public UserSkillsDTO saveUserSkill(@RequestBody UserSkillsDTO userSkillsDTO) {
            return userSkillService.saveUserSkill(userSkillsDTO);
    }

    @PatchMapping
    public UserSkillsDTO updateUserSkill(@RequestBody UserSkillsDTO userSkillsDTO) {
        return userSkillService.updateUserSkill(userSkillsDTO);
    }

    @DeleteMapping("/{userId}/{skillId}")
    public void deleteUserSkill(@PathVariable UUID userId, @PathVariable UUID skillId) {
        userSkillService.deleteUserSkill(userId,skillId);
    }
}
