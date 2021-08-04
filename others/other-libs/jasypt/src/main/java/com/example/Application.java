package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final String message;

    public Application(@Value("${encrypted.message}") String message) {
        this.message = message;
    }

    @Override
    public void run(String... args) {
        System.out.println("message=" + message);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
