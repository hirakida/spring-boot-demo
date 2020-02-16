package com.example;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

public class RedisInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final GenericContainer<?> CONTAINER =
            new GenericContainer<>("redis:5.0").withExposedPorts(6379);

    public static GenericContainer<?> getContainer() {
        return CONTAINER;
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of("spring.redis.port=" + CONTAINER.getMappedPort(6379))
                          .applyTo(configurableApplicationContext.getEnvironment());
    }
}
