package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    private static final AtomicInteger counter = new AtomicInteger();
    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        log.info("findAll");
        return users;
    }

    public User findById(long id) {
        log.info("findById id={}", id);
        return users.stream()
                    .filter(user -> user.getId() == id)
                    .findFirst()
                    .orElseThrow();
    }

    public void insert(String name) {
        log.info("insert name={}", name);
        User user = new User(counter.incrementAndGet(), name);
        users.add(user);
    }

    public User update(User user) {
        log.info("update {}", user);
        return user;
    }

    public void deleteAll() {
        log.info("deleteAll");
        users.clear();
    }
}
