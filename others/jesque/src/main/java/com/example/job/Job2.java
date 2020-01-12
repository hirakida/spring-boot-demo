package com.example.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Job2 implements Runnable {
    private final String message;

    @Override
    public void run() {
        log.info("[{}] message={}", Thread.currentThread().getName(), message);
    }
}
