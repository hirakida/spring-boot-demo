package com.example;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

abstract class AbstractContainerInitializer {
    private static final MySQLContainer<?> CONTAINER =
            new MySQLContainer<>("mysql:8.0").withEnv("TZ", "Asia/Tokyo");

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        CONTAINER.start();
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }
}
