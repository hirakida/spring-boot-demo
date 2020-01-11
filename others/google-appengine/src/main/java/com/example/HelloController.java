package com.example;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public Map<String, Object> hello() {
        return Map.of("message", "Hello, Google App Engine!");
    }
}
