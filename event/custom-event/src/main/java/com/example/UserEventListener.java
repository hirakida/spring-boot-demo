package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventListener {
    @EventListener
    public void handleEvent(UserCreatedEvent event) {
        log.info("UserEvent={}", event.getUser());
    }
}
