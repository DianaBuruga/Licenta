package com.ulbs.careerstartup.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        SecurityContextHolder.clearContext();
        response.getWriter().write(exception.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:4200/v1/callback");
    }
}
