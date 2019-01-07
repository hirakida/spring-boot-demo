package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable int id) {
        return accountRepository.findById(id);
    }

    @PostMapping("/accounts")
    public Account create(@RequestBody Account account) {
        return accountRepository.insert(account);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@RequestBody Account account, @PathVariable int id) {
        return accountRepository.update(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteById(@PathVariable int id) {
        accountRepository.deleteById(id);
    }
}
