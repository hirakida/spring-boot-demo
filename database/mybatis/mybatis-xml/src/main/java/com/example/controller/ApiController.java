package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final AccountMapper accountMapper;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @GetMapping(value = "/accounts", params = "ids")
    public List<Account> findByIds(@RequestParam List<Long> ids) {
        return accountMapper.findByIds(ids);
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable long id) {
        return accountMapper.findById(id);
    }
}
