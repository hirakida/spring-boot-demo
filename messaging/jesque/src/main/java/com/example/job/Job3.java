package com.example.job;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Job3 implements Runnable {

    private final String message;

    @Override
    public void run() {
        log.info("message={}", message);
    }
}
