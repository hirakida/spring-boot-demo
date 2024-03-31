package com.example.cache;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {
    private final List<User> users = List.of(new User(1, "name1"),
                                             new User(2, "name2"),
                                             new User(3, "name3"),
                                             new User(4, "name4"),
                                             new User(5, "name5"));

    public List<User> findAll() {
        return users;
    }
}
