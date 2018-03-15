package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.Account;
import com.example.core.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class ApiController {

    private final AccountService accountService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findOne(@PathVariable int id) {
        return accountService.findOne(id);
    }

    @DeleteMapping
    public void cacheEvict() {
        accountService.cacheEvict();
    }

    @DeleteMapping("/{id}")
    public void cacheEvict(@PathVariable int id) {
        accountService.cacheEvict(id);
    }
}
