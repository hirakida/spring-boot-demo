package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class EventListeners {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventListeners.class);

    @EventListener
    public void handleEvent(WebServerInitializedEvent event) {
        LOGGER.info("WebServerInitializedEvent={}", event.getSource().getPort());
    }

    @EventListener
    public void handleEvent(ServletWebServerInitializedEvent event) {
        LOGGER.info("ServletWebServerInitializedEvent={}", event.getSource().getPort());
    }

    @EventListener
    public void handleEvent(AvailabilityChangeEvent<?> event) {
        AvailabilityState state = event.getState();
        LOGGER.info("AvailabilityChangeEvent state={} {}", state.getClass().getSimpleName(), state);
    }

    @EventListener
    public void handleEvent(RequestHandledEvent event) {
        LOGGER.info("RequestHandledEvent={}", event.getDescription());
    }

    @EventListener
    public void handleEvent(ServletRequestHandledEvent event) {
        LOGGER.info("ServletRequestHandledEvent={}", event.getDescription());
    }
}
