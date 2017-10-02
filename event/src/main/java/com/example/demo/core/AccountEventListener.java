package com.example.demo.core;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountEventListener implements ApplicationListener<AccountEvent> {

    @Override
    public void onApplicationEvent(AccountEvent event) {
        log.info("AccountEventListener::AccountEvent event={}", event.getSource());
    }
}
