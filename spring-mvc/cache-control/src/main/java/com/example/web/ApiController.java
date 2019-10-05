package com.example.web;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/datetime")
    public Map<String, LocalDateTime> datetime() {
        return Map.of("datetime", LocalDateTime.now());
    }
}
