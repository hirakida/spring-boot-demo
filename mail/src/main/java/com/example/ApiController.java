package com.example;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final MailSender mailSender;

    @PostMapping
    public void send(@RequestBody MailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(request.getFrom());
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());
        mailSender.send(message);
    }

    @Data
    public static class MailRequest {
        private String from;
        private String to;
        private String subject;
        private String text;
    }
}
