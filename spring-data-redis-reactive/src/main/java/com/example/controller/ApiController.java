package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.client.StringRedisClient;
import com.example.client.UserRedisClient;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final StringRedisClient stringRedisClient;
    private final UserRedisClient userRedisClient;

    @GetMapping("/strings/{key}")
    public Mono<String> findString(@PathVariable String key) {
        return stringRedisClient.get(key)
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/users/{key}")
    public Mono<User> findUser(@PathVariable String key) {
        return userRedisClient.get(key)
                              .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatus()).build();
    }
}
