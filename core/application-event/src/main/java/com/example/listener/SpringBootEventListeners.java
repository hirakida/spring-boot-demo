package com.example.listener;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SpringBootEventListeners {
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
    public void handleLivenessStateEvent(AvailabilityChangeEvent<LivenessState> event) {
        log.info("AvailabilityChangeEvent<LivenessState> state={}", event.getState());
    }

    @EventListener
    public void handleReadinessStateEvent(AvailabilityChangeEvent<ReadinessState> event) {
        log.info("AvailabilityChangeEvent<ReadinessState> state={}", event.getState());
    }
}
