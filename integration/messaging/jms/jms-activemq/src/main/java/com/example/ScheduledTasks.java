package com.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final JmsClient jmsClient;

    @Scheduled(fixedDelay = 5000)
    public void sendMessages() {
        jmsClient.sendMessage1("Hello");
        jmsClient.sendMessage2("JMS!");
        jmsClient.sendDelayedMessage("Hello, JMS!", 2000);
    }
}
