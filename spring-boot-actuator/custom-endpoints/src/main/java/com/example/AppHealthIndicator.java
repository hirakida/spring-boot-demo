package com.example;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class AppHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        boolean next = new Random().nextBoolean();
        if (next) {
            builder.up().withDetail("detail", next).build();
        } else {
            builder.down().withDetail("detail", next).build();
        }
    }
}
