package com.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final JmsMessageSender jmsMessageSender;

    @Scheduled(fixedDelay = 5000)
    public void sendMessages() {
        jmsMessageSender.sendMessage1("Hello");
        jmsMessageSender.sendMessage2("JMS!");
        jmsMessageSender.sendDelayedMessage("Hello, JMS!", 2000);
    }
}
