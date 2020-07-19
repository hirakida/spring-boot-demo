package com.example;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
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
}
