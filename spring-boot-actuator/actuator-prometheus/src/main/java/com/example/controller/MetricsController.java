package com.example.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

@RestController
public class MetricsController {
    private final Counter counter;
    private final Timer timer;

    public MetricsController(MeterRegistry meterRegistry) {
        counter = Counter.builder("app.web.counter")
                         .tags(Tags.of("type", "Counter"))
                         .register(meterRegistry);
        timer = Timer.builder("app.web.timer")
                     .tags(Tags.of("type", "Timer"))
                     .register(meterRegistry);
        Gauge.builder("app.web.gauge", () -> timer.totalTime(TimeUnit.SECONDS) / counter.count())
             .tags(Tags.of("type", "Gauge"))
             .register(meterRegistry);
    }

    @GetMapping("/count")
    @Timed("count_requests")
    public double count(@RequestParam(defaultValue = "1") long timeout) {
        return timer.record(() -> {
            sleep(timeout);
            counter.increment();
            return counter.count();
        });
    }

    @GetMapping("/time")
    @Timed("time_requests")
    public LocalTime time() {
        return LocalTime.now();
    }

    @GetMapping("/datetime")
    @Timed("datetime_requests")
    public LocalDateTime dateTime() {
        return LocalDateTime.now();
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException ignored) { }
    }
}
