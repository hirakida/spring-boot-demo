package com.example.config;

import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

//    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return Jackson2ObjectMapperBuilder.json()
                                          .serializersByType(Map.of(ZonedDateTime.class,
                                                                    new ZoneDateTimeJsonSerializer()));
    }
}
