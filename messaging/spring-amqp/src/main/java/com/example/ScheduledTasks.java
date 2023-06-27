package com.example;

import static com.example.MessageListeners.QUEUE_NAME;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        rabbitTemplate.convertAndSend(QUEUE_NAME, "Hello!");
    }
}
