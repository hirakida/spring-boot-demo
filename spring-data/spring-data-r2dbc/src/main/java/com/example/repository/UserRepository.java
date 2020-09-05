package com.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.model.User;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
