package com.example;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MetricsService {

    private final CounterService counterService;
    private final GaugeService gaugeService;
    private static final String property = "example.count";

    public void increment() {
        counterService.increment(property);
    }

    public void decrement() {
        counterService.decrement(property);
    }

    public void reset() {
        counterService.reset(property);
    }

    public void gauge(long value) {
        gaugeService.submit(property, value);
    }
}
