package com.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Sender {

    final RabbitTemplate rabbitTemplate;

    public void send(String data) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, data);
    }
}
