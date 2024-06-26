package com.ulbs.careerstartup.api.model;


public record ApplicationEmailRequest(String toEmail, String subject, String name, String message, String deadline) {
}