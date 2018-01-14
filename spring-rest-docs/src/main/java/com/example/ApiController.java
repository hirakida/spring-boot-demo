package com.example;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.Account;
import com.example.core.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account findOne(@PathVariable int id) {
        return accountService.findOne(id);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable int id,
                          @RequestBody @Validated Account account) {
        return accountService.update(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable int id) {
        accountService.delete(id);
    }

    @PostMapping("/accounts")
    public Account create(@RequestBody @Validated Account account) {
        return accountService.create(account);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @SuppressWarnings("serial")
    public static class DataNotFoundException extends RuntimeException {
    }
}
