package com.example.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Account;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

    public List<Account> findAll() {
        log.info("findAll");
        return Arrays.asList(new Account(1, "name1"),
                             new Account(2, "name2"),
                             new Account(3, "name3"));
    }

    public Account findById(long id) {
        log.info("findById id={}", id);
        return new Account(id, "name" + id);
    }

    public Account create(String name) {
        log.info("create name={}", name);
        return new Account(1L, name);
    }

    public Account update(Account account) {
        log.info("update {}", account);
        return account;
    }

    public void deleteById(long id) {
        log.info("deleteById id={}", id);
    }
}
