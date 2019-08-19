package com.example;

import java.time.LocalDateTime;

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
        log.info("[{}] fixedDelay", LocalDateTime.now());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("{}", e.getMessage(), e);
        }
    }

    /**
     * Every 5 seconds
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        log.info("[{}] fixedRate", LocalDateTime.now());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("{}", e.getMessage(), e);
        }
    }

    /**
     * Delay 5 seconds before the first execution
     */
    @Scheduled(initialDelay = 10000, fixedRate = 5000)
    public void initialDelay() {
        log.info("[{}] initialDelay", LocalDateTime.now());
    }
}
