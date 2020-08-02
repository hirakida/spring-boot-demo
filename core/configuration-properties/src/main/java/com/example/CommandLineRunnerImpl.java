package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final Environment environment;
    private final AppProperties properties;
    private final long number1;
    private final long number2;
    private final String str1;
    private final String str2;

    public CommandLineRunnerImpl(Environment environment,
                                 AppProperties properties,
                                 @Value("${app.number.value1}") long number1,
                                 @Value("${app.number.value2:0}") long number2,
                                 @Value("${app.string.value1}") String str1,
                                 @Value("${app.string.value2:@Value default}") String str2) {
        this.environment = environment;
        this.properties = properties;
        this.number1 = number1;
        this.number2 = number2;
        this.str1 = str1;
        this.str2 = str2;
    }

    @Override
    public void run(String... args) {
        log.info("--- @Value ---");
        log.info("number.value1: {}", number1);
        log.info("number.value2: {}", number2);
        log.info("string.value1: {}", str1);
        log.info("string.value2: {}", str2);

        log.info("--- Environment ---");
        log.info("number.value1: {}", environment.getProperty("app.number.value1", Long.class, 0L));
        log.info("number.value2: {}", environment.getProperty("app.number.value2", Long.class, 0L));
        log.info("string.value1: {}", environment.getProperty("app.string.value1"));
        log.info("string.value2: {}",
                 environment.getProperty("app.string.value2", String.class, "Environment default"));

        log.info("--- @ConfigurationProperties ---");
        log.info("number.value1: {}", properties.getNumber().getValue1());
        log.info("number.value2: {}", properties.getNumber().getValue2());
        log.info("string.value1: {}", properties.getString().getValue1());
        log.info("string.value2: {}", properties.getString().getValue2());
    }
}
