package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.api.model.ApplicantsEmailRequest;
import com.ulbs.careerstartup.api.model.ApplicationEmailRequest;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private static final String CONFIRMATION_TEMPLATE = "email.html";
    private static final String APPLICANTS_TEMPLATE = "applicants.html";
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${fromEmail}")
    private String fromEmail;
    @Value("${fromName}")
    private String fromName;

    @Async
    public void sendEmail(String email, String subject, String message) {
        log.info("Email sent to: " + email + " with subject: " + subject + " and message: " + message);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setFrom(fromEmail, fromName);
            javaMailSender.send(mimeMessage);
            log.info("Email sent to: " + email);

        } catch (Exception e) {
            log.error("Error sending email to: " + email + " error: " + e.getMessage());
        }
    }

    @Async
    public void sendConfirmationApplication(ApplicationEmailRequest applicationEmailRequest) {

        log.info("Confirmation message sent to email: {} with subject: {}", applicationEmailRequest.toEmail(), applicationEmailRequest.subject());

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(applicationEmailRequest.toEmail());
            helper.setSubject(applicationEmailRequest.subject());

            Context context = new Context();

            Map<String, Object> properties = new HashMap<>();
            properties.put("name", applicationEmailRequest.name());
            properties.put("message", applicationEmailRequest.message());
            properties.put("deadline", applicationEmailRequest.deadline());

            context.setVariables(properties);

            String html = templateEngine.process("emails/" + CONFIRMATION_TEMPLATE, context);


            helper.setText(html, true);

            log.info("Email content: {}", html);

            javaMailSender.send(message);
            log.info("Email sent successfully");

        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }
    }

    @Async
    public void sendApplicants(ApplicantsEmailRequest applicationEmailRequest) {

        log.info("simpleSend toEmail: {} subJect: {}", applicationEmailRequest.toEmail(), applicationEmailRequest.subject());

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(applicationEmailRequest.toEmail());
            helper.setSubject(applicationEmailRequest.subject());

            InputStreamSource attachment = new ByteArrayResource(applicationEmailRequest.zip().getContent());
            helper.addAttachment(applicationEmailRequest.zip().getName(), attachment);

            Context context = new Context();

            Map<String, Object> properties = new HashMap<>();
            properties.put("name", applicationEmailRequest.name());
            properties.put("position", applicationEmailRequest.position());
            context.setVariables(properties);

            String html = templateEngine.process("emails/" + APPLICANTS_TEMPLATE, context);

            helper.setText(html, true);

            log.info("Email content: {}", html);

            javaMailSender.send(message);
            log.info("Email sent successfully");

        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }
    }

}
