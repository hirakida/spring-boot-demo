package com.example.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    public List<User> findAll() {
        return IntStream.rangeClosed(1, 5)
                        .mapToObj(this::findOne)
                        .collect(Collectors.toList());
    }

    public User findOne(long id) {
        return new User(id, "name" + id);
    }
}
