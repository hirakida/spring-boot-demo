package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.model.Greeting;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableJms
@EnableScheduling
@RequiredArgsConstructor
public class Application {
    private final JmsClient jmsClient;

    @Scheduled(fixedDelay = 5000)
    public void sendMessages() {
        jmsClient.sendMessage("Hello");
        jmsClient.sendMessage(new Greeting("Hello!"));
        jmsClient.sendDelayedMessage("Hello, JMS!", 2000);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
