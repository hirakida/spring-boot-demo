package com.example.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateTimeController {

    @GetMapping("/datetime")
    public Map<String, LocalDateTime> datetime() {
        return Map.of("datetime", LocalDateTime.now());
    }
}
