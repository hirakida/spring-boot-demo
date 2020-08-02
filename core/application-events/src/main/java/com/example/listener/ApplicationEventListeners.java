package com.example.listener;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationEventListeners {
    @EventListener
    public void handleEvent(ApplicationStartedEvent event) {
        log.info("ApplicationStartedEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ApplicationReadyEvent event) {
        log.info("ApplicationReadyEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ApplicationFailedEvent event) {
        log.info("ApplicationFailedEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(AvailabilityChangeEvent<?> event) {
        AvailabilityState state = event.getState();
        log.info("AvailabilityChangeEvent state={} {}", state.getClass().getSimpleName(), state);
    }
}
