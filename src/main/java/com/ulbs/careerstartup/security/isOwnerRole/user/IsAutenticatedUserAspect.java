package com.ulbs.careerstartup.security.isOwnerRole.user;

import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IsAutenticatedUserAspect {

    @Autowired
    private UserService userService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.user.IsAutenticatedUser) && args(userDTO,..)")
    public void checkOwnership(UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = userService.isAutenticatedUser(userDTO.getEmail(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this referral");
        }
    }
}
