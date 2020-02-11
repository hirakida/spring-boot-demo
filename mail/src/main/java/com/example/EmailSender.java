package com.example;

import java.util.Map;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class EmailSender {
    private static final String FROM = "from@example.com";
    private static final String SUBJECT = "subject";
    private static final String BODY = "body";
    private final MailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailSender(MailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void send(String to, Map<String, Object> params) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(to);
        message.setSubject(getSubject(params));
        message.setText(getBody(params));
        mailSender.send(message);
    }

    private String getSubject(Map<String, Object> params) {
        return getText(params, SUBJECT);
    }

    private String getBody(Map<String, Object> params) {
        return getText(params, BODY);
    }

    private String getText(Map<String, Object> params, String template) {
        Context context = new Context();
        context.setVariables(params);
        return templateEngine.process(template, context);
    }
}
