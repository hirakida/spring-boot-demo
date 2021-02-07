package com.example;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final EmailSender emailSender;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        emailSender.send(new EmailData("user1@example.com", "user1", "demo1"));
        emailSender.send(new EmailData("user2@example.com", "user2", "demo2"));
    }
}
