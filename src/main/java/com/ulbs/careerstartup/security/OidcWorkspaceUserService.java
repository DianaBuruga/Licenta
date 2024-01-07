package com.ulbs.careerstartup.security;

import com.ulbs.careerstartup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import static com.ulbs.careerstartup.constant.Constants.GMAIL_SUFFIX;
import static com.ulbs.careerstartup.constant.ExceptionMessages.INVALID_WORKSPACE_EMAIL_MESSAGE;

@Service
@AllArgsConstructor
public class OidcWorkspaceUserService extends OidcUserService {

    private UserService userService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        final OidcUser oidcUser = super.loadUser(userRequest);

        checkIfEmailIsWorkspaceEmail(oidcUser);

        return userService.findByEmailOrSaveUser(oidcUser);
    }

    public void checkIfEmailIsWorkspaceEmail(OidcUser oidcUser) throws OAuth2AuthenticationException {
        String email = oidcUser.getEmail();
        if (email == null || email.endsWith(GMAIL_SUFFIX)) {
            throw new OAuth2AuthenticationException(INVALID_WORKSPACE_EMAIL_MESSAGE);
        }
    }
}