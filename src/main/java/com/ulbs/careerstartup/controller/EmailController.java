package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.api.model.HTMLEmailRequest;
import com.ulbs.careerstartup.apidoc.EmailApiDoc;
import com.ulbs.careerstartup.api.model.EmailRequest;
import com.ulbs.careerstartup.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Email", description = "The Email API")
@RestController
@RequestMapping("/email")
public class EmailController implements EmailApiDoc {
    private EmailService emailService;

    @PostMapping
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getEmail(), emailRequest.getSubject(), emailRequest.getMessage());
    }

    @PostMapping("/sendHTMLEmail")
    public void sendHTMLEmail(@RequestBody HTMLEmailRequest sendHTMLEmailRequest) {
        emailService.htmlSend(sendHTMLEmailRequest);
    }
}
