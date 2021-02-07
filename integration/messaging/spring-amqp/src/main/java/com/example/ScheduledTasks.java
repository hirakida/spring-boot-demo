package com.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final MessageClient messageClient;

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        messageClient.send("Hello!");
    }
}
