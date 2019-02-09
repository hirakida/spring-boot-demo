package com.example.controller;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public Page<Account> findAll(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable int id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @PostMapping("/accounts")
    public Account create(@Validated Form form) {
        Account account = new Account();
        account.setName(form.getName());
        return accountRepository.save(account);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable int id, @Validated Form form) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setName(form.getName());
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts")
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable int id) {
        accountRepository.deleteById(id);
    }

    @Data
    private static class Form {
        private @NotNull String name;
    }
}
