package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final MetricsService metricsService;

    @GetMapping("/increment")
    public String increment() {
        metricsService.increment();
        return "ok";
    }

    @GetMapping("/decrement")
    public String decrement() {
        metricsService.decrement();
        return "ok";
    }

    @GetMapping("/reset")
    public String reset() {
        metricsService.reset();
        return "ok";
    }

    @GetMapping("/gauge")
    public String gauge(@RequestParam long value) {
        metricsService.submit(value);
        return "ok";
    }
}
