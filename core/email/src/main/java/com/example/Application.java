package com.example;

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
        emailSender.send("user1@example.com", "user1");
        emailSender.send("user2@example.com", "user2");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
