package com.example;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@Component
public class ScheduledTasks {
    private final Counter counter1;
    private final Counter counter2;

    public ScheduledTasks(MeterRegistry meterRegistry) {
        counter1 = Counter.builder("app.batch.counter")
                          .tags(Tags.of("task", "task1",
                                        "time", LocalDateTime.now().toString()))
                          .register(meterRegistry);
        counter2 = Counter.builder("app.batch.counter")
                          .tags(Tags.of("task", "task2",
                                        "time", LocalDateTime.now().toString()))
                          .register(meterRegistry);
    }

    @Scheduled(fixedRate = 5000)
    public void task1() {
        counter1.increment();
    }

    @Scheduled(fixedRate = 10000)
    public void task2() {
        counter2.increment();
    }
}
