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
        templates.put("linkedin_post",
                "Hi {{person}},\n\n" +
                        "I hope you're doing well. I came across your recent LinkedIn post regarding the {{post}} role at {{company}}, and I wanted to express my keen interest in this opportunity.\n\n" +
                        "With 2.5+ years of experience in frontend development, I’m currently working at Veersa Technologies as a Senior Software Engineer. My expertise lies in building scalable web applications using ReactJS, Redux, Saga, TypeScript, and JavaScript. Additionally, I’ve contributed to backend development during a recent Spring Boot/Tomcat migration project.\n\n" +
                        "I’ve attached my resume for your reference. I’d be grateful if you could consider me for the role. Looking forward to hearing from you.\n\n" +
                        "Best regards,\n" +
                        "Ankit Bhujeja\n" +
                        "+91-9518614811"
        );

        templates.put("cold_email",
                "Hi {{person}},\n\n" +
                        "I hope you're doing well. I’m reaching out to express my interest in any potential {{post}} opportunities at {{company}}.\n\n" +
                        "I bring 2.5+ years of experience in frontend development, currently working at Veersa Technologies as a Senior Software Engineer. I specialize in building scalable, maintainable web applications using ReactJS, Redux, Saga, TypeScript, and JavaScript, with exposure to backend systems during a Spring Boot/Tomcat migration project.\n\n" +
                        "Please find my resume attached. I’d love to be considered for any relevant roles.\n\n" +
                        "Best regards,\n" +
                        "Ankit Bhujeja\n" +
                        "+91-9518614811"
        );

        templates.put("follow_up",
                "Hi {{person}},\n\n" +
                        "I hope you're doing well. I recently applied for the {{post}} position at {{company}} through {{platform}} and wanted to follow up to reaffirm my interest.\n\n" +
                        "With 2.5+ years of experience in frontend development—mainly using ReactJS, Redux, Saga, TypeScript, and JavaScript—I currently work at Veersa Technologies as a Senior Software Engineer. I’ve also been involved in backend contributions during a Spring Boot and Tomcat migration.\n\n" +
                        "I’ve attached my resume for your review and would love the opportunity to connect.\n\n" +
                        "Warm regards,\n" +
                        "Ankit Bhujeja\n" +
                        "+91-9518614811"
        );

        templates.put("referral_request",
                "Hi {{person}},\n\n" +
                        "I hope you're doing well. I noticed that you’re currently working at {{company}} and wanted to ask if you'd be open to referring me for the {{post}} role listed there.\n\n" +
                        "With 2.5+ years of experience as a frontend developer, I’ve built and maintained scalable web applications using ReactJS, Redux, Saga, TypeScript, and JavaScript at Veersa Technologies. I’ve also contributed to backend systems during a recent Spring Boot and Tomcat migration.\n\n" +
                        "I’ve attached my resume for reference. Please let me know if you need any additional details.\n\n" +
                        "Thanks so much for considering!\n\n" +
                        "Best,\n" +
                        "Ankit Bhujeja\n" +
                        "+91-9518614811"
        );

        templates.put("recruiter_outreach",
                "Hi {{person}},\n\n" +
                        "I hope you're doing well. I’m reaching out to explore if there are any current or upcoming openings for a {{post}} at {{company}}.\n\n" +
                        "I have 2.5+ years of experience in frontend development, working at Veersa Technologies as a Senior Software Engineer. I specialize in ReactJS, Redux, Saga, TypeScript, and JavaScript, and I’ve also worked on backend systems in a Spring Boot/Tomcat migration project.\n\n" +
                        "My resume is attached for your reference. I’d be grateful for the opportunity to be considered for any matching roles.\n\n" +
                        "Warm regards,\n" +
                        "Ankit Bhujeja\n" +
                        "+91-9518614811"
        );
    }


    public void sendEmailWithAttachment(EmailRequest request) throws Exception {
        String body = templates.getOrDefault(request.getTemplateKey(), templates.get("cold_email"));
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