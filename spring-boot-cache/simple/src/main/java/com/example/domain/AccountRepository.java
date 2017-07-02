package com.example.domain;

import org.springframework.stereotype.Repository;

import com.example.domain.Account;

@Repository
public class AccountRepository {

    public Account findById(long id) {
        return Account.builder()
                      .id(id)
                      .name("name" + id)
                      .build();
    }

    public Account update(long id) {
        return Account.builder()
                      .id(id)
                      .name("name" + (id + 1))
                      .build();
    }
}
