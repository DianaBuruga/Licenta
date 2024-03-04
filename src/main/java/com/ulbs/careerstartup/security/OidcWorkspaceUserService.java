package com.ulbs.careerstartup.security;

import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.service.FileService;
import com.ulbs.careerstartup.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.GMAIL_SUFFIX;
import static com.ulbs.careerstartup.constant.ExceptionMessages.INVALID_WORKSPACE_EMAIL_MESSAGE;

@Service
@AllArgsConstructor
@Slf4j
public class OidcWorkspaceUserService extends OidcUserService {

    private UserService userService;
    private FileService fileService;
    private Mapper mapper;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        final OidcUser oidcUser = super.loadUser(userRequest);
        checkIfEmailIsWorkspaceEmail(oidcUser);
        return processOidcUser(oidcUser);
    }

    public OidcUser processOidcUser(OidcUser oidcUser) {
        UserDTO user;
        try {
            user = userService.findByEmail(oidcUser.getEmail());
        } catch (Exception e) {
            user = createAndStoreNewUser(oidcUser);
            storeUserProfileImage(oidcUser, user.getId());
        }
        return buildOidcUser(oidcUser, user);
    }

    private UserDTO createAndStoreNewUser(OidcUser oidcUser) {
        return userService.saveUserAndSetRole(mapper.oidcUserToUserDTO(oidcUser));
    }

    private void storeUserProfileImage(OidcUser oidcUser, UUID userId) {
        FilePK filePK = FilePK.builder()
                .tableId(userId)
                .tableName("user").build();
        try {
            fileService.storeImageFromUrlOrStoreDefault(filePK, oidcUser.getPicture());
        } catch (Exception e) {
            log.error("Failed to store user profile image for user: " + userId, e);
        }
    }

    private OidcUser buildOidcUser(OidcUser originalOidcUser, UserDTO user) {
        return new DefaultOidcUser(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())),
                originalOidcUser.getIdToken(), originalOidcUser.getUserInfo());
    }

    public void checkIfEmailIsWorkspaceEmail(OidcUser oidcUser) throws OAuth2AuthenticationException {
        String email = oidcUser.getEmail();
        if (email == null || email.contains(GMAIL_SUFFIX)) {
            throw new OAuth2AuthenticationException(INVALID_WORKSPACE_EMAIL_MESSAGE);
        }
    }
}