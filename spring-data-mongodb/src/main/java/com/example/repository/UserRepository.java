package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(String name);
}
