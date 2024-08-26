package com.example;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;

import com.example.HelloEventListener.Hello;
import com.example.HelloEventListener.HelloEvent;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final ApplicationEventPublisher publisher;

    public Application(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        final Hello hello = new Hello("Hello! " + LocalDateTime.now());
        publisher.publishEvent(new HelloEvent(hello));
    }
}
