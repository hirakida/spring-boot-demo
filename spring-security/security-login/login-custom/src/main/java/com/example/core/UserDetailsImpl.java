package com.example.core;

import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class UserDetailsImpl extends User {

    private final Account account;

    public UserDetailsImpl(Account account) {
        super(account.getName(), account.getPassword(), account.getRoles());
        this.account = account;
    }
}
