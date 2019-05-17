package com.example.userdetails;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final List<Account> accounts;

    public AccountService(PasswordEncoder passwordEncoder) {
        accounts = List.of(
                new Account(1, "user1", passwordEncoder.encode("pass1"), "user1@example.com",
                            AuthorityUtils.createAuthorityList(Role.USER.getRole())),
                new Account(2, "user2", passwordEncoder.encode("pass2"), "user2@example.com",
                            AuthorityUtils.createAuthorityList(Role.USER.getRole())),
                new Account(3, "user3", passwordEncoder.encode("pass3"), "user3@example.com",
                            AuthorityUtils.createAuthorityList(Role.USER.getRole(), Role.ADMIN.getRole())),
                new Account(4, "user4", passwordEncoder.encode("pass4"), "user4@example.com",
                            AuthorityUtils.NO_AUTHORITIES));
    }

    public Optional<Account> findByUsername(String username) {
        return accounts.stream()
                       .filter(account -> account.getName().equals(username))
                       .findFirst();
    }

    public Account findOne(UserDetails userDetails) {
        return findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(userDetails.getUsername()));
    }
}
