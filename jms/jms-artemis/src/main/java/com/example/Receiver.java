package com.example;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

    public static final String TEXT_QUEUE = "text.queue";
    public static final String MESSAGE_QUEUE = "message.queue";

    @JmsListener(destination = TEXT_QUEUE)
    public void receiveText(String text) {
        log.info("text: {}", text);
    }

    @JmsListener(destination = MESSAGE_QUEUE, concurrency = "1-5")
    public void receiveMessage(Message<String> message) {
        log.info("message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }
}
