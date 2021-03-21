package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final Counter counter;

    public CommandLineRunnerImpl(MeterRegistry meterRegistry) {
        counter = Counter.builder("app.batch.counter")
                         .tags(Tags.of("result", "SUCCESS"))
                         .register(meterRegistry);
    }

    @Override
    public void run(String... args) {
        counter.increment();
    }
}
