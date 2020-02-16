package com.example;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void run(String... args) throws Exception {
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

        TimeUnit.SECONDS.sleep(1);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
