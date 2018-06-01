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
public class AccountEventListener implements ApplicationListener<AccountEvent> {

    @Override
    public void onApplicationEvent(AccountEvent event) {
        log.info("AccountEventListener::AccountEvent event={}", event.getSource());
    }
}
