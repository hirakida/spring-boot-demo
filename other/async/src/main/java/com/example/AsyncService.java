package com.example;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

    @Async
    public void run() {
        log.info("AsyncService::run start");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            log.error("error:", e);
        }
        log.info("AsyncService::run end");
    }

    @Async
    public void error() {
        log.info("AsyncService::error start");
        throw new RuntimeException("Async error");
    }
}
