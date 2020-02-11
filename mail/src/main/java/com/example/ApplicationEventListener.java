package com.example;

import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {
    private final EmailSender emailSender;

    public ApplicationEventListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        emailSender.send("to1@example.com", Map.of("name", "user1"));
        emailSender.send("to2@example.com", Map.of("name", "user2"));
    }
}
