package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener {
    public static final String QUEUE1 = "jms.queue1";
    public static final String QUEUE2 = "jms.queue2";
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsMessageListener.class);

    @JmsListener(destination = QUEUE1)
    public void receiveMessage1(String message) {
        LOGGER.info("message1: {}", message);
    }

    @JmsListener(destination = QUEUE2)
    public void receiveMessage2(MessageModel message) {
        LOGGER.info("message2: {}", message);
    }
}
