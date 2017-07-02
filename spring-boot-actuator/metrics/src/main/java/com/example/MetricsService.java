package com.example;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MetricsService {

    private static final String property = "example.count";

    private final CounterService counterService;

    private final GaugeService gaugeService;

    public void increment() {
        counterService.increment("example.increment");
        counterService.increment(property);
    }

    public void decrement() {
        counterService.increment("example.decrement");
        counterService.decrement(property);
    }

    public void reset() {
        counterService.increment("example.reset");
        counterService.reset(property);
    }

    public void gauge(long value) {
        gaugeService.submit(property, value);
    }
}
