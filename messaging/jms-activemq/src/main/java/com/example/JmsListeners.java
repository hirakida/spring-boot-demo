package com.example;

import static com.example.JmsConfig.DESTINATION1;
import static com.example.JmsConfig.DESTINATION2;
import static com.example.JmsConfig.DESTINATION3;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsListeners {
    @JmsListener(destination = DESTINATION1)
    public void receiveMessage(String text) {
        log.info("text message: {}", text);
    }

    @JmsListener(destination = DESTINATION2)
    public void receiveMessage(Message<Greeting> message) {
        log.info("message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }

    @JmsListener(destination = DESTINATION3, concurrency = "1-3")
    public void receiveDelayedMessage(Message<String> message) {
        log.info("delayed message: payload={} headers={}", message.getPayload(), message.getHeaders());
    }
}
