package com.ulbs.careerstartup.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping
    public String welcome() {
        return "Welcome to unsecured page!";
    }

    @GetMapping("/login")
    public String redirectToGoogle() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping
    @RequestMapping("/home")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
    public String home() {
        return "Welcome to secured page!";
    }

    @GetMapping
    @RequestMapping("/isAuthenticated")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
    public Boolean isAuthenticated(){
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}
