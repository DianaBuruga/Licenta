package com.ulbs.careerstartup.security.isOwnerRole.postedJob;

import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.service.PostedJobService;
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
public class IsPostedJobAspect {

    @Autowired
    private PostedJobService postedJobService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.postedJob.IsPostedJobOwner) && args(postedJobDTO,..)")
    public void checkOwnership(PostedJobDTO postedJobDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = postedJobService.isPostedJobOwner(postedJobDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this posted job");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.postedJob.IsPostedJobOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = postedJobService.isPostedJobOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this posted job");
    }
}
