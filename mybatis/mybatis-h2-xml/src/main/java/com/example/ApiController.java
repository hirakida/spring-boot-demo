package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ApiController {

    final AccountMapper accountMapper;

    @GetMapping("/accounts")
    public List<Account> accounts() {
        return accountMapper.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account account(@PathVariable long id) {
        return accountMapper.findOne(id);
    }
}
