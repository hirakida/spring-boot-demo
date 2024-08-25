package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class Application {
    @Bean
    public LoggingEventListener loggingEventListener() {
        return new LoggingEventListener();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
