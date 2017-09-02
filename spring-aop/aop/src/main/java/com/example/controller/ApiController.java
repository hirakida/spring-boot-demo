package com.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Account;
import com.example.service.AccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class ApiController {

    final AccountService accountService;

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable long id) {
        log.info("get id={}", id);
        return accountService.getAccount(id);
    }

    @GetMapping("/accounts/{id}/name")
    public String getName(@PathVariable long id) {
        log.info("get id={}", id);
        return accountService.getName(id);
    }

    @PutMapping("/accounts/{id}")
    public Account putAccount(@PathVariable long id, @RequestParam String name) {
        log.info("put id={}", id);
        return accountService.putAccount(id, name);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable long id) {
        log.info("delete id={}", id);
        accountService.deleteAccount(id);
    }

    @PostMapping("/accounts")
    public Account postAccount(@PathVariable String name) {
        log.info("post name={}", name);
        return accountService.postAccount(name);
    }

    @GetMapping("/exception")
    public String exception() {
        throw new RuntimeException();
    }
}
