package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final RedisMessagePublisher publisher;

    @PostMapping("/{message}")
    public void publish(@PathVariable String message) {
        publisher.publish(message);
    }
}
