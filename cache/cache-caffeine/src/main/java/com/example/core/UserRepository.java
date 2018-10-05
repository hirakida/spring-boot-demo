package com.example.core;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    public User findById(long id) {
        return new User(id, "name" + id);
    }
}
