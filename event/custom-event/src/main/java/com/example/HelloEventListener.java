package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HelloEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloEventListener.class);

    @EventListener
    public void handleEvent(HelloEvent event) {
        LOGGER.info("HelloEvent={}", event.hello());
    }

    public record Hello(String message) {}

    public record HelloEvent(Hello hello) {}
}
