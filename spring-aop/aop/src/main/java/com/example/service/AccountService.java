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
        log.info("getAccount id={}", id);
        return Account.of(id);
    }

    public String getName(long id) {
        log.info("getName id={}", id);
        return "name" + id;
    }

    public Account putAccount(long id, String name) {
        log.info("putAccount id={} name={}", id, name);
        return Account.of(id, name);
    }

    public Account postAccount(String name) {
        log.info("postAccount name={}", name);
        return Account.of(1L, name);
    }

    public void deleteAccount(long id) {
        log.info("deleteAccount id={}", id);
    }
}
