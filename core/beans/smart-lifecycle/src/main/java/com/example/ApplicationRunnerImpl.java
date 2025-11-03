package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunnerImpl.class);
    private final MyLifecycle myLifecycle;

    public ApplicationRunnerImpl(MyLifecycle myLifecycle) {
        this.myLifecycle = myLifecycle;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("isRunning: {}", myLifecycle.isRunning());
        myLifecycle.stop(() -> LOGGER.info("stop callback"));
        LOGGER.info("isRunning: {}", myLifecycle.isRunning());
    }
}
