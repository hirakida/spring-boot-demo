package com.example;

import java.time.LocalDateTime;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {
    private final RestTemplate restTemplate;

    public ApiController(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @GetMapping("/datetime")
    public LocalDateTime getDateTime() {
        return restTemplate.getForObject("http://localhost:8082/datetime", LocalDateTime.class);
    }
}
