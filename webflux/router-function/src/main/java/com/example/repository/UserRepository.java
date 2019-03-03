package com.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.entity.User;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Flux<User> findByName(String name);
}
