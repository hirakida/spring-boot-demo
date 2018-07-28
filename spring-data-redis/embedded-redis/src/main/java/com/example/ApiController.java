package com.example;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final RedisClient RedisClient;

    @GetMapping("/{key}")
    public Optional<String> get(@PathVariable String key) {
        return RedisClient.get(key);
    }
}
