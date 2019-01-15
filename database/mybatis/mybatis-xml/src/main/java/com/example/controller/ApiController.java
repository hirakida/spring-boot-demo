package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final AccountMapper accountMapper;

    @GetMapping("/accounts")
    public List<Account> accounts() {
        return accountMapper.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account account(@PathVariable long id) {
        return accountMapper.findOne(id);
    }
}
