package com.ulbs.careerstartup.security.isOwnerRole.language;

import com.ulbs.careerstartup.dto.LanguageDTO;
import com.ulbs.careerstartup.service.LanguageService;
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
public class IsLanguageAspect {

    @Autowired
    private LanguageService languageService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.language.IsLanguageOwner) && args(languageDTO,..)")
    public void checkOwnership(LanguageDTO languageDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = languageService.isOwner(languageDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this language");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.language.IsLanguageOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = languageService.isOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this language");
    }
}
