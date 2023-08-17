package com.teksen.githubdataemailer.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendStyledHTMLFormattedEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);

        String styledContent = "<html><head><style>"
                + "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f2f2f2; }"
                + "h2 { color: #007bff; margin-bottom: 10px; }"
                + "table { width: 100%; border-collapse: collapse; margin-top: 10px; }"
                + "th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }"
                + "tr:nth-child(even) { background-color: #f2f2f2; }"
                + "</style></head><body>"
                + content
                + "</body></html>";

        helper.setText(styledContent, true);

        javaMailSender.send(message);
    }




}
