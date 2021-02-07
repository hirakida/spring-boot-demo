package com.example;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public Map<String, Object> hello() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        return Map.of("message", "Hello! " + LocalTime.now());
    }
}
