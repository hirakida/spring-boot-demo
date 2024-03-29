package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@Configuration
@EnableReactiveMongoAuditing
public class MongoConfig {
    @Bean
    public LoggingEventListener loggingEventListener() {
        return new LoggingEventListener();
    }
}
