package com.ulbs.careerstartup.dto;

public record UserInfo(
        String sub,
        String name,
        String given_name,
        String family_name,
        String picture,
        String email,
        String phone,
        boolean email_verified,
        String locale
) { }
