package com.example.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.event.UserEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventListener implements ApplicationListener<UserEvent> {
    @Override
    public void onApplicationEvent(UserEvent event) {
        log.info("event={}", event.getSource());
    }

    @EventListener
    public void handleEvent(UserEvent event) {
        log.info("UserEvent={}", event.getSource());
    }
}
