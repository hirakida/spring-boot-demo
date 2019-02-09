package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.User;
import com.example.core.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @DeleteMapping("/users")
    public void cacheEvict() {
        userService.cacheEvict();
    }

    @DeleteMapping("/users/{id}")
    public void cacheEvict(@PathVariable int id) {
        userService.cacheEvict(id);
    }
}
