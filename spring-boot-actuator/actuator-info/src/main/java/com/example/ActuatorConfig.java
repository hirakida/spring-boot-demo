package com.example;

import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.info.SimpleInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {
    @Bean
    public SimpleInfoContributor springBootVersionInfoContributor() {
        return new SimpleInfoContributor("spring-boot-version", SpringBootVersion.getVersion());
    }
}
