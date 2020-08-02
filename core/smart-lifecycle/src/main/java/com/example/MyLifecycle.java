package com.example;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyLifecycle implements SmartLifecycle {
    private volatile boolean running;

    @Override
    public void start() {
        log.info("start");
        running = true;
    }

    @Override
    public void stop() {
    }

    @Override
    public void stop(Runnable callback) {
        log.info("stop");
        running = false;
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            callback.run();
        }).start();
    }

    @Override
    public boolean isRunning() {
        log.info("isRunning: {}", running);
        return running;
    }
}
