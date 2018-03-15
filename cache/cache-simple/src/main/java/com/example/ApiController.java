package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.User;
import com.example.core.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ApiController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping
    public void cacheEvictAll() {
        userService.cacheEvictAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable long id) {
        return userService.findOne(id);
    }

    @PutMapping("/{id}")
    public User cachePut(@PathVariable long id) {
        return userService.cachePut(id);
    }

    @DeleteMapping("/{id}")
    public void cacheEvict(@PathVariable long id) {
        userService.cacheEvict(id);
    }
}
