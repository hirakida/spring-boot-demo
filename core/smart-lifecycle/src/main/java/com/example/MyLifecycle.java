package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class MyLifecycle implements SmartLifecycle {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyLifecycle.class);
    private volatile boolean running;

    @Override
    public void start() {
        LOGGER.info("start");
        running = true;
    }

    @Override
    public void stop() {
        LOGGER.info("stop");
        running = false;
    }

    @Override
    public void stop(Runnable callback) {
        LOGGER.info("stop callback");
        stop();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            callback.run();
        }).start();
    }

    @Override
    public boolean isRunning() {
        LOGGER.info("isRunning: {}", running);
        return running;
    }
}
