package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.client.UserRedisClient;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserRedisClient userRedisClient;

    @GetMapping("/users/{id}")
    public User get(@PathVariable long id) {
        return userRedisClient.get(id)
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable long id) {
        userRedisClient.delete(id);
    }
}
