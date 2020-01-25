package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private static final String URI = "http://hello";
    private final RestTemplate restTemplate;

    @GetMapping
    public String hello() {
        return restTemplate.getForObject(URI, String.class);
    }
}
