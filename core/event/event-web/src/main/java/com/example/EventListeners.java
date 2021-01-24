package com.example;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventListeners {
    @EventListener
    public void handleEvent(WebServerInitializedEvent event) {
        log.info("WebServerInitializedEvent={}", event.getSource().getPort());
    }

    @EventListener
    public void handleEvent(ServletWebServerInitializedEvent event) {
        log.info("ServletWebServerInitializedEvent={}", event.getSource().getPort());
    }

    @EventListener
    public void handleEvent(AvailabilityChangeEvent<?> event) {
        AvailabilityState state = event.getState();
        log.info("AvailabilityChangeEvent state={} {}", state.getClass().getSimpleName(), state);
    }

    @EventListener
    public void handleEvent(RequestHandledEvent event) {
        log.info("RequestHandledEvent={}", event.getDescription());
    }

    @EventListener
    public void handleEvent(ServletRequestHandledEvent event) {
        log.info("ServletRequestHandledEvent={}", event.getDescription());
    }
}
