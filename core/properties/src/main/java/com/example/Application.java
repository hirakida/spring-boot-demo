package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@ConditionalOnProperty(prefix = "app", name = "enabled", havingValue = "true")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
