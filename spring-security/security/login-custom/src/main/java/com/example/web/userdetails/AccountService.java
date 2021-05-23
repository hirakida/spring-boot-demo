package com.example.web.userdetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.web.model.Account;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {
    private final List<Account> accounts = new ArrayList<>();

    public void addAll(List<Account> accounts) {
        this.accounts.addAll(accounts);
    }

    public Account findByUsername(String username) {
        return accounts.stream()
                       .filter(account -> account.getName().equals(username))
                       .findFirst()
                       .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Account findOne(UserDetails userDetails) {
        log.info("userDetails={}", userDetails);
        return findByUsername(userDetails.getUsername());
    }

    public Account findOne(String username) {
        log.info("username={}", username);
        return findByUsername(username);
    }
}
