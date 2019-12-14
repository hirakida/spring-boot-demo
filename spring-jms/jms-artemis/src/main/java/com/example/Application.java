package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;

@EnableJms
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final JmsSender jmsSender;

    @Scheduled(fixedDelay = 5000)
    public void scheduled() {
        jmsSender.sendText("hello!");
        jmsSender.sendMessage("hello!!");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
