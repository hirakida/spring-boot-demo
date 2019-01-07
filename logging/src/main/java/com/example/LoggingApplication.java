package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LoggingApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }

    public static void main(String[] args) {
        SpringApplication.run(LoggingApplication.class, args);
    }
}
