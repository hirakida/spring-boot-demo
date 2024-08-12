package com.example;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloController {
    private static final AtomicInteger COUNTER = new AtomicInteger();

    @GetMapping("/hello")
    public Map<String, String> hello() {
        final int count = COUNTER.incrementAndGet();
        if (count % 10 == 0) {
            throw new ResponseStatusException(GATEWAY_TIMEOUT);
        }
        return Map.of("message", "Hello! " + count);
    }
}
