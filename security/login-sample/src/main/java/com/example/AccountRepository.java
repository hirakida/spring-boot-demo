package com.example;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

@Component
public class AccountRepository {

    private List<Account> accounts;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        accounts = ImmutableList.of(new Account(1L, "user1", passwordEncoder.encode("pass1")),
                                    new Account(2L, "user2", passwordEncoder.encode("pass2")));
    }

    public Optional<Account> findByName(String name) {
        return accounts.stream()
                       .filter(account -> StringUtils.equals(account.getName(), name))
                       .findFirst();
    }
}
