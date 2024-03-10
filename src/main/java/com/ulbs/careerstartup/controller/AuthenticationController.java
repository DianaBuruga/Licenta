package com.ulbs.careerstartup.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping
    public String welcome() {
        return "Welcome to unsecured page!";
    }

    @GetMapping
    @RequestMapping("/home")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
    public String home() {
        return "Welcome to secured page!";
    }
}
