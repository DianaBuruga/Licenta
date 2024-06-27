package com.ulbs.careerstartup.security.isOwnerRole.company;

import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.service.CompanyService;
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
public class IsCompanyAspect {

    @Autowired
    private CompanyService companyService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.company.IsCompanyOwner) && args(courseDTO,..)")
    public void checkOwnership(CourseDTO courseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = companyService.isOwner(courseDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("The user is not a representative of this company");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.company.IsCompanyOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner =  companyService.isOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("The user is not a representative of this company");
    }
}
