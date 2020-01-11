package com.example;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping
    public String hello() {
        return "Hello, Jib!";
    }

    @GetMapping("/datetime")
    public LocalDateTime datetime() {
        return LocalDateTime.now();
    }
}
