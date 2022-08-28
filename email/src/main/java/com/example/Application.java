package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final EmailSender emailSender;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        emailSender.send(new EmailData("user1@example.com", "user1", "demo1"));
        emailSender.send(new EmailData("user2@example.com", "user2", "demo2"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
