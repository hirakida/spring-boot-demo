package com.example.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

@RestController
public class MeterController {
    private final Counter counter;
    private final Timer timer;

    public MeterController(MeterRegistry meterRegistry) {
        counter = Counter.builder("app.time.counter")
                         .tags(Tags.of("method", "GET", "uri", "/time"))
                         .register(meterRegistry);
        timer = Timer.builder("app.time.timer")
                     .tags(Tags.of("method", "GET", "uri", "/time"))
                     .register(meterRegistry);
        Gauge.builder("app.time.gauge", () -> timer.totalTime(TimeUnit.SECONDS) / counter.count())
             .tags(Tags.of("method", "GET", "uri", "/time"))
             .register(meterRegistry);
    }

    @GetMapping("/time")
    @Timed("time_requests")
    public LocalTime time() {
        timer.record((Runnable) counter::increment);
        return LocalTime.now();
    }

    @GetMapping("/datetime")
    @Timed("datetime_requests")
    public LocalDateTime dateTime() {
        return LocalDateTime.now();
    }
}
