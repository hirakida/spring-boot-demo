package com.example;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AppHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean next = new Random().nextBoolean();
        if (next) {
            return Health.up().withDetail("detail", next).build();
        } else {
            return Health.down().withDetail("detail", next).build();
        }
    }
}
