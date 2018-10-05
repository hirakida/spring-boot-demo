package com.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Flux<User> findByName(String name);

    Flux<User> findByAge(int age);
}
