package com.example.job;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Job1 implements Runnable {
    private final String message;

    @Override
    public void run() {
        log.info("[{}] message={}", Thread.currentThread().getName(), message);
    }
}
