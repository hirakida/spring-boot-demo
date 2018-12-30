package com.example.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByName(username);
    }

    public Account findOne(UserDetails userDetails) {
        log.info("userDetails={}", userDetails);
        return findOne(userDetails.getUsername());
    }

    public Account findOne(String username) {
        log.info("username={}", username);
        return accountRepository.findByName(username)
                                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
