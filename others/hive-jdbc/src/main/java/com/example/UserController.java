package com.example;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping("/users")
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
