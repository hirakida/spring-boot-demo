package com.example.config;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.JsonSerializer;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>();
        serializers.put(ZonedDateTime.class, new ZoneDateTimeJsonSerializer());
        return Jackson2ObjectMapperBuilder.json()
                                          .serializersByType(serializers);
    }
}
