package com.ulbs.careerstartup.security;

import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.dto.UserInfo;import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final WebClient userInfoClient;
    private final AuthenticationService authenticationService;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfo userInfo = userInfoClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", token)
                        .build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();
        if (userInfo == null) {
            throw new IllegalStateException("User is not authenticated");
        }
        UserDTO userDTO = authenticationService.saveUserIfDoesNotExist(userInfo);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("phone", userDTO.getPhone());
        attributes.put("email", userDTO.getEmail());
        attributes.put("name", userDTO.getName());
        attributes.put("id", userDTO.getId());
        return new OAuth2IntrospectionAuthenticatedPrincipal(userInfo.email(), attributes,
                Collections.singleton(new SimpleGrantedAuthority(userDTO.getRole().name())));
    }
}
