package com.example.config;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return Jackson2ObjectMapperBuilder.json()
                                          .propertyNamingStrategy(SNAKE_CASE)
                                          .modules(new Jdk8Module());
    }
}
