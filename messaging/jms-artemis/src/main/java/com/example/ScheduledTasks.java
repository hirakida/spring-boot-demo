package com.example;

import static com.example.JmsListeners.DESTINATION;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final JmsMessagingTemplate messagingTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        messagingTemplate.convertAndSend(DESTINATION, "Hello!");
    }
}
