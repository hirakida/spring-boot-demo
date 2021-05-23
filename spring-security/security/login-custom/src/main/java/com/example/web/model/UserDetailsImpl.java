package com.example.web.model;

import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class UserDetailsImpl extends User {
    private final Account account;

    public UserDetailsImpl(Account account) {
        super(account.getName(), account.getPassword(), account.getRoles());
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
