package com.example.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final List<Account> accounts;

    public AccountService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        accounts = IntStream.rangeClosed(1, 5)
                            .mapToObj(this::account)
                            .collect(Collectors.toList());
    }

    public Optional<Account> findByUsername(String username) {
        return accounts.stream()
                       .filter(account -> account.getName().equals(username))
                       .findFirst();
    }

    public Account findOne(UserDetails userDetails) {
        log.info("userDetails={}", userDetails);
        return findOne(userDetails.getUsername());
    }

    public Account findOne(String username) {
        log.info("username={}", username);
        return accounts.stream()
                       .filter(account -> account.getName().equals(username))
                       .findFirst()
                       .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private Account account(long id) {
        Account account = new Account();
        account.setId(id);
        account.setName("user" + id);
        account.setEmail("name" + id + "@example.com");
        account.setPassword(passwordEncoder.encode("password" + id));
        if (id == 1) {
            account.setRoles(AuthorityUtils.NO_AUTHORITIES);
        } else if (id > 1 && id < 4) {
            account.setRoles(AuthorityUtils.createAuthorityList("ROLE_USER"));
        } else if (id > 3) {
            account.setRoles(AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
        }
        return account;
    }
}
