package com.example;

import static com.example.Application.DESTINATION;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsListeners {
    @JmsListener(destination = DESTINATION)
    public void receiveMessage(String text) {
        log.info("text: {}", text);
    }
}
