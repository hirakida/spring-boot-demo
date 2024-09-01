package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    public static final String QUEUE_NAME = "queue1";
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(@Payload String data) {
        LOGGER.info(data);
    }
}
