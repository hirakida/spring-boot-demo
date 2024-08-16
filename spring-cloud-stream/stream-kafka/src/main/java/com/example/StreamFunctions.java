package com.example;

import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StreamFunctions {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamFunctions.class);
    private static final AtomicInteger COUNTER = new AtomicInteger();

    @Bean
    public Supplier<Data> dataSupplier() {
        return () -> {
            final Data data = new Data(COUNTER.incrementAndGet(),
                                       LocalTime.now(),
                                       UUID.randomUUID().toString());
            LOGGER.info("Supplier: {}", data);
            return data;
        };
    }

    @Bean
    public Function<Data, Data> uppercaseFunction() {
        return data -> {
            LOGGER.info("Function: {}", data);
            return new Data(data.id, data.time(), data.message.toUpperCase());
        };
    }

    @Bean
    public Consumer<Data> dataConsumer() {
        return data -> LOGGER.info("Consumer: {}", data);
    }

    public record Data(int id, LocalTime time, String message) {}
}
