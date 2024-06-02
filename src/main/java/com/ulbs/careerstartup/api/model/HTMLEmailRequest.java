package com.ulbs.careerstartup.api.model;


public record HTMLEmailRequest (String toEmail, String subject, String name, String message) {}