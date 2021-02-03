package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return repository.findById(id).orElseThrow();
    }
}
