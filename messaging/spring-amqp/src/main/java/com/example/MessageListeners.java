package com.example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListeners {
    public static final String QUEUE_NAME = "text.queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(@Payload String data) {
        log.info(data);
    }
}
