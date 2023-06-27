package com.example;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsListeners {
    public static final String DESTINATION = "jms.queue1";

    @JmsListener(destination = DESTINATION)
    public void receiveMessage(String text) {
        log.info("text message: {}", text);
    }
}
