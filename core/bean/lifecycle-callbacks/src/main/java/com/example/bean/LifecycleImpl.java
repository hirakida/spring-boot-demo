package com.example.bean;

import org.springframework.context.SmartLifecycle;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class LifecycleImpl implements SmartLifecycle {
    private String name;
    private String message;

    @Override
    public void start() {
        log.info("start");
    }

    @Override
    public void stop() {
        log.info("stop");
    }

    @Override
    public boolean isRunning() {
        log.info("isRunning");
        return true;
    }

    @Override
    public int getPhase() {
        log.info("getPhase");
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isAutoStartup() {
        log.info("isAutoStartup");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        log.info("stop");
        callback.run();
    }
}
