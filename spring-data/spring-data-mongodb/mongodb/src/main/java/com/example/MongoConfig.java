package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@Configuration
@EnableMongoAuditing
public class MongoConfig {
    @Bean
    public LoggingEventListener loggingEventListener() {
        return new LoggingEventListener();
    }
}
