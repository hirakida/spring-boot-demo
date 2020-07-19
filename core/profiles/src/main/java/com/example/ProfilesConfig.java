package com.example;

import java.time.Instant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ProfilesConfig {
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
}
