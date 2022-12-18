package com.example.userdetails;

import static com.example.model.Role.ADMIN;
import static com.example.model.Role.USER;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Account;
import com.example.model.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final List<Account> accounts = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        List<Account> accounts = List.of(create(1, List.of(ADMIN)),
                                         create(2, List.of(USER)),
                                         create(3, List.of(ADMIN, USER)),
                                         create(4, List.of()));
        this.accounts.addAll(accounts);
    }

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

    private Account create(long id, List<Role> roles) {
        String name = "user" + id;
        return new Account(id, name, passwordEncoder.encode(name), name + "@example.com", roles);
    }
}
