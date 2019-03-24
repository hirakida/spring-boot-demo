package com.example.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.web.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByName(username);
    }

    public Account findOne(UserDetails userDetails) {
        return accountRepository.findByName(userDetails.getUsername())
                                .orElseThrow(() -> new UsernameNotFoundException(userDetails.getUsername()));
    }

    public void create(String name, String email, String password, Role role) {
        final Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account.setRoles(role.toGrantedAuthority());
        accountRepository.create(account);
    }
}
