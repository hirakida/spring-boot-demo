package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable int id) {
        return accountRepository.findById(id).orElseThrow();
    }
}
