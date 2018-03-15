package com.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Account;
import com.example.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/{id}")
    public Account get(@PathVariable long id) {
        return accountRepository.get(id)
                                .orElseThrow(DataNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        accountRepository.delete(id);
    }
}
