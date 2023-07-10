package com.example;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByName(String name);

    @Query("{'name': ?0}")
    List<User> findByUsername(String name);
}
