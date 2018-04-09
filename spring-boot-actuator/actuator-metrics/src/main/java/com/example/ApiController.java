package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final MetricsService metricsService;

    @PostMapping("/increment")
    public void increment() {
        metricsService.increment();
    }

    @PostMapping("/decrement")
    public void decrement() {
        metricsService.decrement();
    }

    @PostMapping("/reset")
    public void reset() {
        metricsService.reset();
    }

    @PostMapping("/gauge")
    public void gauge(@RequestParam long value) {
        metricsService.submit(value);
    }
}
