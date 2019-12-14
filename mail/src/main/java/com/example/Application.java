package com.example;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final EmailSender emailSender;

    public Application(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void run(String... args) {
        emailSender.send("to1@example.com", Map.of("name", "user1"));
        emailSender.send("to2@example.com", Map.of("name", "user2"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
