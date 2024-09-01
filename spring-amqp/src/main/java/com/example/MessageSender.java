package com.example;

import static com.example.MessageListener.QUEUE_NAME;

import java.time.LocalTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        rabbitTemplate.convertAndSend(QUEUE_NAME, "Hello! " + LocalTime.now());
    }
}
