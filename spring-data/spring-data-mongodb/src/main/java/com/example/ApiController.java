package com.example;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public Page<Account> findAll(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @DeleteMapping("/accounts")
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @PostMapping("/accounts")
    public void save(@Validated Account account) {
        accountRepository.save(account);
    }

    @GetMapping("/accounts/{id}")
    public Account findOne(@PathVariable String id) {
        return accountRepository.findOne(id);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable String id) {
        accountRepository.delete(id);
    }

    @PutMapping("/accounts/{id}")
    public void save(@PathVariable String id, @Validated Account account) {
        accountRepository.save(account);
    }
}
