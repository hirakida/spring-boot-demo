package com.example;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        boolean next = new Random().nextBoolean();
        log.info("nex={}", next);
        if (next) {
            builder.up().withDetail("detail", next);
        } else {
            builder.down().withDetail("detail", next);
        }
    }
}
