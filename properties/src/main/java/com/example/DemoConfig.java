package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ConditionalOnProperty(prefix = "demo", name = "enabled", havingValue = "true")
@Slf4j
public class DemoConfig {

    public DemoConfig() {
        log.info("Constructor");
    }
}
