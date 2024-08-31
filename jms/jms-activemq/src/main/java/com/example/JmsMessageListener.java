package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener {
    public static final String DESTINATION1 = "jms.queue1";
    public static final String DESTINATION2 = "jms.queue2";
    public static final String DESTINATION3 = "jms.queue3";
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsMessageListener.class);

    @JmsListener(destination = DESTINATION1)
    public void receiveMessage(String text) {
        LOGGER.info("{}", text);
    }

    @JmsListener(destination = DESTINATION2)
    public void receiveMessage(Message<MessageModel> message) {
        LOGGER.info("Message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }

    @JmsListener(destination = DESTINATION3, concurrency = "1-3")
    public void receiveDelayedMessage(Message<String> message) {
        LOGGER.info("Delayed message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }
}
