package com.example;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

    public Account getAccount(long id) {
        log.info("getAccount {}", id);
        return Account.builder()
                      .id(id)
                      .name("name" + id)
                      .build();
    }
}
