package com.example;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface AccountRepository extends R2dbcRepository<Account, Integer> {

    Mono<Account> findByName(String name);
}
