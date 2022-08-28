package com.example;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailSender {
    private static final String FROM = "from@example.com";
    private static final String SUBJECT = "subject";
    private static final String BODY = "body";

    private final MailSender mailSender;
    private final SpringTemplateEngine emailTemplateEngine;

    public void send(EmailData data) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(data.getTo());
        message.setSubject(getSubject(data));
        message.setText(getBody(data));
        mailSender.send(message);
    }

    private String getSubject(EmailData data) {
        Context context = new Context();
        context.setVariable("name", data.getName());
        return emailTemplateEngine.process(SUBJECT, context);
    }

    private String getBody(EmailData data) {
        Context context = new Context();
        context.setVariable("name", data.getName());
        context.setVariable("message", data.getMessage());
        return emailTemplateEngine.process(BODY, context);
    }
}
