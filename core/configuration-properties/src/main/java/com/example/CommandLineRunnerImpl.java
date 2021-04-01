package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.properties.AppProperties;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final Environment environment;
    private final AppProperties properties;

    public CommandLineRunnerImpl(Environment environment,
                                 AppProperties properties,
                                 @Value("${app.test1.value1}") long number1,
                                 @Value("${app.test1.value2:0}") long number2,
                                 @Value("${app.test1.text1}") String text1,
                                 @Value("${app.test1.text2:@Value}") String text2) {
        this.environment = environment;
        this.properties = properties;

        log.info("test1.value1: {}", number1);
        log.info("test1.value2: {}", number2);
        log.info("test1.text1: {}", text1);
        log.info("test1.text2: {}", text2);
    }

    @Override
    public void run(String... args) {
        log.info("test1.value1: {}", environment.getProperty("app.test1.value1", Long.class));
        log.info("test1.value2: {}", environment.getProperty("app.test1.value2", Long.class, 0L));
        log.info("test1.text1: {}", environment.getProperty("app.test1.text1"));
        log.info("test1.text2: {}", environment.getProperty("app.test1.text2", String.class, "Environment"));

        log.info("--- @ConfigurationProperties ---");
        log.info("test1: {}", properties.getTest1());
        log.info("test2: {}", properties.getTest2());
    }
}
