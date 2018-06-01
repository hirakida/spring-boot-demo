package com.example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receive(@Payload String data) {
        log.info(data);
    }
}
