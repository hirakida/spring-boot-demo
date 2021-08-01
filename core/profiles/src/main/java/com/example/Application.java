package com.example;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ConfigurationPropertiesScan
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final Environment environment;
    private final AppProperties properties;

    @Override
    public void run(String... args) {
        log.info("System: {} ", System.getProperty("spring.profiles.active"));
        log.info("Environment: {}", environment.getProperty("spring.profiles.active"));
        log.info("activeProfiles: {}", Arrays.toString(environment.getActiveProfiles()));

        Profiles dev = Profiles.of("dev");
        Profiles production = Profiles.of("production");
        log.info("acceptsProfiles(dev): {}", environment.acceptsProfiles(dev));
        log.info("acceptsProfiles(production): {}", environment.acceptsProfiles(production));

        log.info("message: {}", properties.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
