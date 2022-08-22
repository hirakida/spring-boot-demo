package com.example;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;

public interface AccountRepository extends R2dbcRepository<Account, Integer> {
    Flux<Account> findByName(String name);
}
