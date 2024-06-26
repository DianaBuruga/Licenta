package com.ulbs.careerstartup.security.isOwnerRole.experience;

import com.ulbs.careerstartup.dto.ExperienceDTO;
import com.ulbs.careerstartup.service.ExperienceService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.ulbs.careerstartup.security.isOwnerRole.RoleVerificationUtil.hasAdminRole;

@Aspect
@Component
public class IsExperienceAspect {

    @Autowired
    private ExperienceService experienceService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.experience.IsExperienceOwner) && args(experienceDTO,..)")
    public void checkOwnership(ExperienceDTO experienceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = experienceService.isExperienceOwner(experienceDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this experience");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.experience.IsExperienceOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = experienceService.isExperienceOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this experience");
    }
}
