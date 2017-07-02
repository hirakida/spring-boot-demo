package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ApiController {

    final MetricsService metricsService;

    @GetMapping("/increment")
    public void increment() {
        metricsService.increment();
    }

    @GetMapping("/decrement")
    public void decrement() {
        metricsService.decrement();
    }

    @GetMapping("/reset")
    public void reset() {
        metricsService.reset();
    }

    @GetMapping("/gauge")
    public void gauge(@RequestParam long value) {
        metricsService.gauge(value);
    }
}
