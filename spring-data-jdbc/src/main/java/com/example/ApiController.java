package com.example;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/api/accounts")
    public List<Account> findAll() {
        return accountRepository.findAllList();
    }

    @GetMapping("/api/accounts/{id}")
    public Account findById(@PathVariable int id) {
        return accountRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
