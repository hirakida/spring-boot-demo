package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final RedisMessagePublisher publisher;

    @PostMapping("/")
    public void publish(@RequestParam String message) {
        publisher.publish(message);
    }
}
