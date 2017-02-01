package com.example;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public Account findAccount(String userName) {
        Account account = new Account(1L, userName, "password");
        return account;
    }
}
