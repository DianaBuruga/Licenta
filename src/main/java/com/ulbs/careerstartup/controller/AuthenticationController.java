package com.ulbs.careerstartup.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.ulbs.careerstartup.apidoc.AuthenticationApiDoc;
import com.ulbs.careerstartup.dto.TokenDto;
import com.ulbs.careerstartup.dto.UrlDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

import static com.sun.activation.registries.LogSupport.log;

@RestController
@Tag(name = "Authentication", description = "The Authentication API")
@Slf4j
public class AuthenticationController implements AuthenticationApiDoc {

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientSecret}")
    private String clientSecret;


    @GetMapping("/auth/url")
    public UrlDto auth() {
        String url = new GoogleAuthorizationCodeRequestUrl(clientId,
                "http://localhost:4200/profile",
                Arrays.asList(
                        "email",
                        "profile",
                        "openid")).build();

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
}
