package com.example;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public User cacheable(@PathVariable long id) {
        return userService.cacheable(id);
    }

    @DeleteMapping("/{id}")
    public void cacheEvict(@PathVariable long id) {
        userService.cacheEvict(id);
    }
}
