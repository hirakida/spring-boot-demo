package com.example;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerImpl.class);
    private final Environment environment;

    public CommandLineRunnerImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        log.info("System: " + System.getProperty("spring.profiles.active"));
        log.info("Environment: " + environment.getProperty("spring.profiles.active"));
        log.info("activeProfiles: " + Arrays.toString(environment.getActiveProfiles()));

        Profiles dev = Profiles.of("dev");
        Profiles production = Profiles.of("production");
        log.info("acceptsProfiles(dev): " + environment.acceptsProfiles(dev));
        log.info("acceptsProfiles(production): " + environment.acceptsProfiles(production));

        log.info("message: " + environment.getProperty("app.message"));
    }
}
