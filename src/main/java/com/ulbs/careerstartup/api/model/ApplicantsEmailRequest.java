package com.ulbs.careerstartup.api.model;

import com.ulbs.careerstartup.dto.FileDTO;

public record ApplicantsEmailRequest(String toEmail, String subject, String name, String position, FileDTO zip) {
}
