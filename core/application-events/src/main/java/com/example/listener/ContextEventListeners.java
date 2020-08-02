package com.example.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ContextEventListeners {
    @EventListener
    public void handleEvent(ContextRefreshedEvent event) {
        log.info("ContextRefreshedEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ContextStartedEvent event) {
        log.info("ContextStartedEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ContextStoppedEvent event) {
        log.info("ContextStoppedEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ContextClosedEvent event) {
        log.info("ContextClosedEvent={}", event.getSource());
    }
}
