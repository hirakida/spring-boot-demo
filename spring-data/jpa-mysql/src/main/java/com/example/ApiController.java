package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Account;
import com.example.domain.AccountRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class ApiController {

    final AccountRepository accountRepository;

    @GetMapping
    public Page<Account> accounts(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Account account(@PathVariable int id) {
        return accountRepository.findOne(id);
    }
}
