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
import com.example.domain.User;
import com.example.domain.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    final AccountRepository accountRepository;
    final UserRepository userRepository;

    @GetMapping("/accounts")
    public Page<Account> accounts(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @GetMapping("/accounts/{id}")
    public Account account(@PathVariable int id) {
        return accountRepository.findOne(id);
    }

    @GetMapping("/users")
    public Page<User> users(@PageableDefault Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
