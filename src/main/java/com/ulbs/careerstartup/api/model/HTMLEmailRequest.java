package com.ulbs.careerstartup.api.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Data
public class HTMLEmailRequest {
    private String email;
    private String subject;
    private String name;

    @JsonIgnore
    private String templateName = "email.html";
}
