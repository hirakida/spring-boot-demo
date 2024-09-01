package com.example;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FixedTasks {
    /**
     * 5 seconds after the end of the last invocation
     */
    @Scheduled(fixedDelay = 5000)
    public void fixedDelay() {
        log.info("[{}][fixedDelay]", LocalTime.now());
    }

    /**
     * Every 5 seconds
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        log.info("[{}][fixedRate=5000]", LocalTime.now());
    }

    @Scheduled(initialDelay = 10000, fixedRate = 5000)
    public void initialDelay() {
        log.info("[{}][initialDelay=10000]", LocalTime.now());
    }
}
