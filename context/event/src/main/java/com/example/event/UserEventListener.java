package com.example.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * My Event
 * (Use ApplicationListener interface)
 */
@Component
@Slf4j
public class UserEventListener implements ApplicationListener<UserEvent> {

    @Override
    public void onApplicationEvent(UserEvent event) {
        log.info("UserEventListener::UserEvent event={}", event.getSource());
    }
}
