package com.example;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final Environment environment;

    public HelloController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello! " + Arrays.toString(environment.getActiveProfiles());
    }
}
