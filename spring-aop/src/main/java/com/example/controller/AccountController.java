package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        log.info("findAll");
        return accountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable long id) {
        log.info("findById id={}", id);
        return accountService.findById(id);
    }

    @PostMapping("/accounts")
    public Account create(@RequestBody Request request) {
        log.info("create {}", request);
        return accountService.create(request.getName());
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable long id,
                          @RequestBody Request request) {
        log.info("update id={} name={}", id, request.getName());
        return accountService.update(new Account(id, request.getName()));
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteById(@PathVariable long id) {
        log.info("deleteById id={}", id);
        accountService.deleteById(id);
    }

    @Data
    public static class Request {
        private String name;
    }
}
