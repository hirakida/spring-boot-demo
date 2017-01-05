package com.example;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SampleService {

    @Async
    public void run() {
        log.info("SampleService::run start");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            log.error("error:", e);
        }
        log.info("SampleService::run end");
    }

    @Async
    public void error() {
        log.info("SampleService::error start");
        throw new RuntimeException("Async error");
    }
}