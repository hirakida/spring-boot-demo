package com.example;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractMongoInitializer {
    private static final GenericContainer<?> CONTAINER =
            new GenericContainer<>(DockerImageName.parse("mongo:4.4"))
                    .withEnv("MONGO_INITDB_ROOT_USERNAME", "root")
                    .withEnv("MONGO_INITDB_ROOT_PASSWORD", "pass")
                    .withEnv("MONGO_INITDB_DATABASE", "admin")
                    .withExposedPorts(27017);

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        CONTAINER.start();
        registry.add("spring.data.mongodb.host", CONTAINER::getHost);
        registry.add("spring.data.mongodb.port", () -> CONTAINER.getMappedPort(27017));
    }
}
