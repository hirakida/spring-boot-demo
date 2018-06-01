package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

@Component
public class AppEndpoint extends AbstractEndpoint<AppMetrics> {

    private static final String ENDPOINT_ID = "app";
    private static final AtomicInteger count = new AtomicInteger(0);

    public AppEndpoint() {
        super(ENDPOINT_ID);
    }

    @Override
    public AppMetrics invoke() {
        return new AppMetrics(count.getAndIncrement(), LocalDateTime.now());
    }
}
