package com.example;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public Map<String, Object> hello() {
        LocalDateTime start = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        LocalDateTime end = LocalDateTime.now();
        return Map.of("message", "Hello", "start", start, "end", end);
    }
}
