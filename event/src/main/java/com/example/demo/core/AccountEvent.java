package com.example.demo.core;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class AccountEvent extends ApplicationEvent {

    public AccountEvent(Account account) {
        super(account);
    }
}
