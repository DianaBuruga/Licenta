package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.api.model.HTMLEmailRequest;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

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
    public void htmlSend(HTMLEmailRequest htmlEmailRequest) {

        log.info("simpleSend toEmail: {} subJect: {} templateName: {}", htmlEmailRequest.getEmail(), htmlEmailRequest.getSubject(), htmlEmailRequest.getTemplateName());

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(htmlEmailRequest.getEmail());
            helper.setSubject(htmlEmailRequest.getSubject());

            Context context = new Context();

            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("name", htmlEmailRequest.getName());
            //properties.put("offerings", htmlEmailRequest.getOfferings());

            context.setVariables(properties);

            String html = templateEngine.process("emails/" + htmlEmailRequest.getTemplateName(), context);


            helper.setText(html, true);

            log.info("Email content: {}", html);

            javaMailSender.send(message);
            log.info("Email sent successfully");

        }
        catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }

    }
}
