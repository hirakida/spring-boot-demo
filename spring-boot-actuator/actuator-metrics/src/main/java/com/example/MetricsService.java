package com.example;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private static final String COUNT_METRICS = "example.count";
    private static final String INCREMENT_METRICS = "example.increment";
    private static final String DECREMENT_METRICS = "example.decrement";
    private static final String RESET_METRICS = "example.reset";

    private final CounterService counterService;
    private final GaugeService gaugeService;

    public void increment() {
        counterService.increment(INCREMENT_METRICS);
        counterService.increment(COUNT_METRICS);
    }

    public void decrement() {
        counterService.increment(DECREMENT_METRICS);
        counterService.decrement(COUNT_METRICS);
    }

    public void reset() {
        counterService.increment(RESET_METRICS);
        counterService.reset(COUNT_METRICS);
    }

    public void submit(long value) {
        gaugeService.submit(COUNT_METRICS, value);
    }
}
