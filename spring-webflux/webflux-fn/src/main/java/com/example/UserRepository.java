package com.example;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;

public interface UserRepository extends R2dbcRepository<User, Integer> {
    Flux<User> findByName(String name);
}
