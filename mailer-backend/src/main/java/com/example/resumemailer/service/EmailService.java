package com.example.resumemailer.service;

import com.example.resumemailer.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final Map<String, String> templates = new HashMap<>();

    static {
        templates.put("default", "Hi {{person}},\n\nI hope you are doing well. I am writing to express my interest in the {{post}} role at {{company}}. Please find my resume attached.\n\nBest regards,\nAnkit Bhujeja");
        templates.put("friendly", "Hello {{person}},\n\nI came across the {{post}} opening at {{company}} and was excited to apply. I've attached my resume for your review.\n\nWarm regards,\nAnkit Bhujeja");
    }

    public void sendEmailWithAttachment(EmailRequest request) throws Exception {
        String body = templates.getOrDefault(request.getTemplateKey(), templates.get("default"));
        body = body.replace("{{person}}", request.getPerson())
                .replace("{{post}}", request.getPost())
                .replace("{{company}}", request.getCompany());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(request.getEmail());
        helper.setSubject("Application for " + request.getPost() + " at " + request.getCompany());
        helper.setText(body);

        helper.addAttachment("resume.pdf", new ClassPathResource("static/resume.pdf"));

        mailSender.send(message);
    }
}