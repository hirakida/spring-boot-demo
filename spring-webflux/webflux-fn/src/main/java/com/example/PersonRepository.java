package com.example;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;

public interface PersonRepository extends R2dbcRepository<Person, Integer> {
    Flux<Person> findByName(String name);
}
