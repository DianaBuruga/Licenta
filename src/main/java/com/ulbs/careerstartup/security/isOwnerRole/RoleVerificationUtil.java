package com.ulbs.careerstartup.security.isOwnerRole;

public class RoleVerificationUtil {
    public static boolean hasAdminRole(org.springframework.security.core.Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }
}
