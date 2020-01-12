package com.example;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.fluentd.logger.FluentLogger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FluentLoggerTasks {
    private final FluentLogger fluentLogger = FluentLogger.getLogger("es");
    private static final AtomicInteger count = new AtomicInteger();

    @Scheduled(fixedRate = 10000)
    public void tag1() {
        fluentLogger.log("tag1", Map.of("datetime", LocalDateTime.now(),
                                        "clock", Clock.systemDefaultZone().millis()));
    }

    @Scheduled(fixedRate = 1000)
    public void tag2() {
        fluentLogger.log("tag2", Map.of("count", count.incrementAndGet()));
    }
}
