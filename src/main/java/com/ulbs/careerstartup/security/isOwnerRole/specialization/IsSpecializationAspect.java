package com.ulbs.careerstartup.security.isOwnerRole.specialization;

import com.ulbs.careerstartup.dto.SpecializationDTO;
import com.ulbs.careerstartup.service.SpecializationService;
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
public class IsSpecializationAspect {

    @Autowired
    private SpecializationService specializationService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.specialization.IsSpecializationOwner) && args(specializationDTO,..)")
    public void checkOwnership(SpecializationDTO specializationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = specializationService.isSpecializationOwner(specializationDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this referral");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.specialization.IsSpecializationOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = specializationService.isSpecializationOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this referral");
    }
}
