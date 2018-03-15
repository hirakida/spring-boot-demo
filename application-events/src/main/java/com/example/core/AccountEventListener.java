package com.example.core;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * My Event
 * (use ApplicationListener interface)
 */
@Component
@Slf4j
public class AccountEventListener implements ApplicationListener<AccountEvent> {

    @Override
    public void onApplicationEvent(AccountEvent event) {
        log.info("AccountEventListener::AccountEvent event={}", event.getSource());
    }
}
