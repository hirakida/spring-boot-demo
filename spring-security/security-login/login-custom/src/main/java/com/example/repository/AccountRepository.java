package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public class AccountRepository {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private final List<Account> accounts = new ArrayList<>();

    public List<Account> findAll() {
        return accounts;
    }

    public Optional<Account> findByName(String name) {
        return accounts.stream()
                       .filter(account -> account.getName().equals(name))
                       .findFirst();
    }

    public void create(Account account) {
        account.setId(ATOMIC_INTEGER.getAndIncrement());
        accounts.add(account);
    }
}
