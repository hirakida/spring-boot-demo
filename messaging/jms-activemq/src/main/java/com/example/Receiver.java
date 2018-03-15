package com.example;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

    @JmsListener(destination = AppConfig.TEXT_QUEUE_NAME)
    public void receiveText(String text) {
        log.info("text: {}", text);
    }

    @JmsListener(destination = AppConfig.MESSAGE_QUEUE, concurrency = "1-5")
    public void receiveMessage(Message<String> message) {
        log.info("message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }

    @JmsListener(destination = AppConfig.DELAYED_QUEUE, concurrency = "1-5")
    public void receiveDelayedMessage(Message<String> message) {
        log.info("delayed message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }
}
