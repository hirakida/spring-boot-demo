package com.example.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.event.UserEvent;

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
        log.info("AccountEventListener::UserEvent event={}", event.getSource());
    }
}
