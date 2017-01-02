package com.example;

import org.springframework.stereotype.Repository;

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
