package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    private final List<User> users;

    public UserService() {
        users = IntStream.rangeClosed(0, 10)
                         .mapToObj(i -> new User(i, "name" + i))
                         .collect(Collectors.toList());
    }

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

    public User create(String name) {
        log.info("create name={}", name);
        return new User(1L, name);
    }

    public User update(User user) {
        log.info("update {}", user);
        return user;
    }

    public void deleteById(long id) {
        log.info("deleteById id={}", id);
    }
}
