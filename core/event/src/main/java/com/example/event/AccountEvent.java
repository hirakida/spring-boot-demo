package com.example.event;

import org.springframework.context.ApplicationEvent;

import com.example.entity.Account;

@SuppressWarnings("serial")
public class AccountEvent extends ApplicationEvent {

    public AccountEvent(Account account) {
        super(account);
    }
}
