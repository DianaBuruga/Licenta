package com.ulbs.careerstartup.security.isOwnerRole.jobHistory;


import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.service.JobHistoryService;
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
public class IsJobHistoryAspect {

    @Autowired
    private JobHistoryService jobHistoryService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.jobHistory.IsJobHistoryOwner) && args(jobHistoryDTO,..)")
    public void checkOwnership(JobHistoryDTO jobHistoryDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = jobHistoryService.isOwner(jobHistoryDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this job history");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.jobHistory.IsJobHistoryOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = jobHistoryService.isOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this job history");
    }
}
