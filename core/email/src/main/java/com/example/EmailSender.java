package com.example;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Component
public class EmailSender {
    private static final String FROM = "from@example.com";
    private static final String SUBJECT = "subject";
    private static final String BODY = "body";
    private static final String MESSAGE = "Hello!";

    private final MailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public EmailSender(MailSender mailSender, SpringTemplateEngine springTemplateEngine) {
        this.mailSender = mailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    public void send(String to, String name) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(FROM);
        mailMessage.setTo(to);
        mailMessage.setSubject(getSubject(name));
        mailMessage.setText(getBody(name));
        mailSender.send(mailMessage);
    }

    private String getSubject(String name) {
        Context context = new Context();
        context.setVariable("name", name);
        return springTemplateEngine.process(SUBJECT, context);
    }

    private String getBody(String name) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("message", MESSAGE);
        return springTemplateEngine.process(BODY, context);
    }
}
