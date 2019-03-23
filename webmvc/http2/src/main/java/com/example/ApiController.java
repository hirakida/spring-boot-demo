package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/")
    public Map<String, Object> index() {
        return Map.of("datetime", LocalDateTime.now());
    }
}
