package com.example.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EnvironmentController {
    private final Environment environment;

    @GetMapping("/environment")
    public Response environment() {
        return new Response(environment.getProperty("app.string.value1"),
                            environment.getProperty("app.string.value2", String.class, "Environment default"),
                            environment.getProperty("app.number.value1", Long.class, 0L),
                            environment.getProperty("app.number.value2", Long.class, 0L));
    }
}
