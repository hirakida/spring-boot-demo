package com.example.web;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/datetime")
    public Map<String, LocalDateTime> datetime() {
        return Map.of("datetime", LocalDateTime.now());
    }

    @GetMapping("/locale")
    public Map<String, Locale> locale() {
        return Map.of("locale", Locale.getDefault());
    }
}
