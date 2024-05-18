package com.ulbs.careerstartup.api.model;

import lombok.Data;

@Data
public class HTMLEmailRequest {
    private String email;
    private String subject;
    private String name;
    private String templateName = "email.html";
}
