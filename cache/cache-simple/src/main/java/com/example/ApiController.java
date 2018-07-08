package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    @DeleteMapping("/users")
    public void cacheEvictAll() {
        userService.cacheEvictAll();
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable long id) {
        return userService.findOne(id);
    }

    @PutMapping("/users/{id}")
    public User cachePut(@PathVariable long id) {
        return userService.cachePut(id);
    }

    @DeleteMapping("/users/{id}")
    public void cacheEvict(@PathVariable long id) {
        userService.cacheEvict(id);
    }
}
