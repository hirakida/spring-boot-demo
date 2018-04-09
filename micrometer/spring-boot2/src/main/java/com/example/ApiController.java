package com.example;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@RestController
public class ApiController {

    @GetMapping("/metrics/hello")
    @Timed("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/metrics/now")
    @Timed("now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
