package com.ulbs.careerstartup.api.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String email;
    private String subject;
    private String message;
}
