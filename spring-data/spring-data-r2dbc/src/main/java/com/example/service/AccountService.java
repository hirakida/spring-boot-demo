package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.example.model.Account;
import com.example.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionalOperator transactionalOperator;

    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    public Mono<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    public Mono<Account> insert(Account account) {
        return transactionalOperator.transactional(accountRepository.insert(account));
    }

    public Mono<Account> update(Account account) {
        return transactionalOperator.transactional(accountRepository.update(account));
    }
}
