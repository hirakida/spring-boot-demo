package com.example;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    public Mono<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    public Mono<Void> deleteById(int id) {
        return accountRepository.deleteById(id);
    }

    public Flux<Account> saveAll(List<Account> accounts) {
        return accountRepository.saveAll(accounts);
    }

    public Mono<Account> create(Account account) {
        return Mono.fromSupplier(() -> {
            account.setCreatedAt(LocalDateTime.now());
            return account;
        }).flatMap(accountRepository::save);
    }

    public Mono<Account> update(Account account) {
        return accountRepository.findById(account.getId())
                                .flatMap(entity -> {
                                    entity.setName(account.getName());
                                    return accountRepository.save(entity);
                                });
    }
}
