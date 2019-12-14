package com.example;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyJmsListener {
    public static final String TEXT_QUEUE = "text.queue";
    public static final String MESSAGE_QUEUE = "message.queue";
    public static final String DELAYED_MESSAGE_QUEUE = "delayed_message.queue";

    @JmsListener(destination = TEXT_QUEUE)
    public void receiveText(String text) {
        log.info("text: {}", text);
    }

    @JmsListener(destination = MESSAGE_QUEUE, concurrency = "1-5")
    public void receiveMessage(Message<String> message) {
        log.info("message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }

    @JmsListener(destination = DELAYED_MESSAGE_QUEUE, concurrency = "1-5")
    public void receiveDelayedMessage(Message<String> message) {
        log.info("delayed message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }
}
