package com.example.listeners;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationEventListeners {
    @EventListener
    public void handleEvent(AvailabilityChangeEvent<?> event) {
        AvailabilityState state = event.getState();
        log.info("AvailabilityChangeEvent state={} {}", state.getClass().getSimpleName(), state);
    }

    @EventListener
    public void handleEvent(ApplicationStartedEvent event) {
        log.info("ApplicationStartedEvent={}", event.getTimestamp());
    }

    @EventListener
    public void handleEvent(ApplicationReadyEvent event) {
        log.info("ApplicationReadyEvent={}", event.getTimestamp());
    }

    @EventListener
    public void handleEvent(ApplicationFailedEvent event) {
        log.info("ApplicationFailedEvent={}", event.getTimestamp());
    }

    @EventListener
    public void handleEvent(ContextRefreshedEvent event) {
        log.info("ContextRefreshedEvent={}", event.getTimestamp());
    }

    @EventListener
    public void handleEvent(ContextStartedEvent event) {
        log.info("ContextStartedEvent={}", event.getTimestamp());
    }

    @EventListener
    public void handleEvent(ContextStoppedEvent event) {
        log.info("ContextStoppedEvent={}", event.getTimestamp());
    }

    @EventListener
    public void handleEvent(ContextClosedEvent event) {
        log.info("ContextClosedEvent={}", event.getTimestamp());
    }
}
