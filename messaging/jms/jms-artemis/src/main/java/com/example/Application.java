package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJms
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class Application {
    public static final String DESTINATION = "jms.queue1";
    private final JmsMessagingTemplate messagingTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        messagingTemplate.convertAndSend(DESTINATION, "Hello!");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
