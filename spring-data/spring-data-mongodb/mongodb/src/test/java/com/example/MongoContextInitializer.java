package com.example;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    static final GenericContainer<?> CONTAINER = new GenericContainer<>(DockerImageName.parse("mongo:4.4"))
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "root")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "pass")
            .withEnv("MONGO_INITDB_DATABASE", "admin")
            .withExposedPorts(27017);

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues values = TestPropertyValues.of(
                "spring.data.mongodb.host=" + CONTAINER.getHost(),
                "spring.data.mongodb.port=" + CONTAINER.getMappedPort(27017));
        values.applyTo(configurableApplicationContext);
    }
}
