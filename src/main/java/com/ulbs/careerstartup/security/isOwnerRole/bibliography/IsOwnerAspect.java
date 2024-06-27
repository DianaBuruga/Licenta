package com.ulbs.careerstartup.security.isOwnerRole.bibliography;

import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.service.BibliographyService;
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
public class IsOwnerAspect {

    @Autowired
    private BibliographyService bibliographyService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.bibliography.IsBibliographyOwner) && args(bibliographyDTO,..)")
    public void checkOwnership(BibliographyDTO bibliographyDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = bibliographyService.isOwner(bibliographyDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this bibliography");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.bibliography.IsBibliographyOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = bibliographyService.isOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this bibliography");
    }
}
