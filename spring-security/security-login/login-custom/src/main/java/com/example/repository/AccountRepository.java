package com.example.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public class AccountRepository {
    private final PasswordEncoder passwordEncoder;
    private final List<Account> accounts;

    public AccountRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        accounts = IntStream.rangeClosed(1, 5)
                            .mapToObj(this::create)
                            .collect(Collectors.toList());
    }

    public List<Account> findAll() {
        return accounts;
    }

    public Optional<Account> findByName(String name) {
        return accounts.stream()
                       .filter(account -> account.getName().equals(name))
                       .findFirst();
    }

    private Account create(long id) {
        final Account account = new Account();
        account.setId(id);
        account.setName("user" + id);
        account.setEmail("name" + id + "@example.com");
        account.setPassword(passwordEncoder.encode("password" + id));
        account.setRoles(getRoles(id));
        return account;
    }

    private static List<GrantedAuthority> getRoles(long id) {
        if (id == 1) {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
        if (id == 2) {
            return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        }
        return AuthorityUtils.NO_AUTHORITIES;
    }
}
