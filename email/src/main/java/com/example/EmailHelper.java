package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Map;


@Component
@RequiredArgsConstructor
public class EmailHelper {
    private static final String FROM = "demo@example.com";
    private final MailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    void send(String to, Map<String, String> params) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(to);
        message.setSubject(getSubject(params));
        message.setText(getBody(params));
        mailSender.send(message);
    }

    private String getSubject(Map<String, String> params) {
        Context context = new Context();
        params.forEach(context::setVariable);
        return templateEngine.process("subject", context);
    }

    private String getBody(Map<String, String> params) {
        Context context = new Context();
        params.forEach(context::setVariable);
        return templateEngine.process("body", context);
    }
}
