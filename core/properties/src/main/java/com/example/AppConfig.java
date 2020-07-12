package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "enabled", havingValue = "true")
@Slf4j
public class AppConfig {

    public AppConfig() {
        log.info("Constructor");
    }
}
