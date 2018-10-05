package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@Configuration
public class MongodbConfig {

    @Bean
    public LoggingEventListener loggingEventListener() {
        return new LoggingEventListener();
    }
}
