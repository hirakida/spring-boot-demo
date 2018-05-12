package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> findAll(@PageableDefault Pageable pageable) {
        return StreamSupport.stream(accountRepository.findAll(pageable).spliterator(), false)
                            .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    public Account findOne(@PathVariable String id) {
        return accountRepository.findOne(id);
    }

    @PostMapping("/accounts")
    public Account create(@RequestParam String name) {
        Account account = new Account();
        account.setName(name);
        return accountRepository.save(account);
    }
}
