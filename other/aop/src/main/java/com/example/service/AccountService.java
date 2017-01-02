package com.example.service;

import org.springframework.stereotype.Service;

import com.example.Account;

import lombok.extern.slf4j.Slf4j;

/**
 * AOPはpublicメソッドが対象
 * クラス内からpublicメソッドを呼んでもAOPは実行されない
 */
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

    public String getName(long id) {
        log.info("getName {}", id);
        return "name" + id;
    }

    public Account putAccount(long id, String name) {
        log.info("putAccount {} {}", id, name);
        return Account.builder()
                      .id(id)
                      .name(name)
                      .build();
    }

    public Account postAccount(String name) {
        log.info("postAccount {}", name);
        return Account.builder()
                      .id(1L)
                      .name(name)
                      .build();
    }

    public void deleteAccount(long id) {
        log.info("deleteAccount {}", id);
    }
}
