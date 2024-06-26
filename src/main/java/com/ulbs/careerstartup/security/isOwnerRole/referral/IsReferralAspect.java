package com.ulbs.careerstartup.security.isOwnerRole.referral;


import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.service.ReferralService;
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
public class IsReferralAspect {

    @Autowired
    private ReferralService referralService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.referral.IsReferralOwner) && args(referralDTO,..)")
    public void checkOwnership(ReferralDTO referralDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = referralService.isOwner(referralDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this referral");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.referral.IsReferralOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = referralService.isOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this referral");
    }
}
