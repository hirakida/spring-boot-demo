package com.example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {

    @RabbitListener(queues = AmqpConfig.TEXT_QUEUE)
    public void receive(@Payload String data) {
        log.info(data);
    }
}
