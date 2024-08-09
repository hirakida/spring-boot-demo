package com.example;

import java.time.LocalDateTime;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthIndicatorImpl implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up()
                     .withDetail("time", LocalDateTime.now())
                     .build();
    }
}
