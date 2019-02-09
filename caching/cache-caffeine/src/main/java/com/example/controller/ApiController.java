package com.example.controller;

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

    @GetMapping("/users/{id}")
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/users/{id}")
    public void cacheEvict(@PathVariable long id) {
        userService.cacheEvict(id);
    }
}
