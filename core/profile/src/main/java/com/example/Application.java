package com.example;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Profile("dev")
    @Bean
    public Instant devBean() {
        log.info("dev");
        return Instant.now();
    }

    @Profile("production")
    @Bean
    public Instant productionBean() {
        log.info("production");
        return Instant.now();
    }

    @Profile("!dev")
    @Bean
    public Instant notDevBean() {
        log.info("!dev");
        return Instant.now();
    }

    @Profile("!production")
    @Bean
    public Instant notProductionBean() {
        log.info("!production");
        return Instant.now();
    }

    @Profile("dev | production")
    @Bean
    public Instant orBean() {
        log.info("dev | production");
        return Instant.now();
    }

    @Profile("dev & production")
    @Bean
    public Instant andBean() {
        log.info("dev & production");
        return Instant.now();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
