package com.ulbs.careerstartup.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.ulbs.careerstartup.apidoc.AuthenticationApiDoc;
import com.ulbs.careerstartup.constant.ServiceEnum;
import com.ulbs.careerstartup.dto.IsOwner;
import com.ulbs.careerstartup.dto.Role;
import com.ulbs.careerstartup.dto.TokenDto;
import com.ulbs.careerstartup.dto.UrlDto;
import com.ulbs.careerstartup.service.AuthorizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.UUID;

import static com.sun.activation.registries.LogSupport.log;
import static com.ulbs.careerstartup.security.isOwnerRole.RoleVerificationUtil.hasAdminRole;

@RestController
@Tag(name = "Authentication", description = "The Authentication API")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController implements AuthenticationApiDoc {

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientSecret}")
    private String clientSecret;

    private final AuthorizationService authorizationService;

    @GetMapping("/auth/url")
    public UrlDto auth() {
        String url = new GoogleAuthorizationCodeRequestUrl(clientId,
                "http://localhost:4200/profile",
                Arrays.asList(
                        "email",
                        "profile",
                        "openid"))
                .set("prompt", "select_account")
                .build();

        return new UrlDto(url);
    }

    @GetMapping("/auth/callback")
    public TokenDto callback(@RequestParam("code") String code) {

        String token = "";
        try {
            token = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(), new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    "http://localhost:4200/profile"
            ).execute().getAccessToken();
        } catch (IOException e) {
            log(e.getMessage());
        }

        return new TokenDto(token);
    }

    @GetMapping("/auth/role")
    public Role getUserRole(){
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        return new Role(role);
    }


    @GetMapping("/isOwner/{endpoint}/{id}")
    public IsOwner isOwner(@PathVariable String endpoint, @PathVariable UUID id, Principal principal) {
        Authentication authentication = (Authentication) principal;
        Boolean isOwner = authorizationService.callMethodByName(ServiceEnum.getByValue(endpoint).toString(), id, authentication);
        return new IsOwner(isOwner || hasAdminRole(authentication));
    }
}
