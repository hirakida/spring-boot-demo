package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfig {
    @Bean
    public GraphQLScalarType localDateTimeScalarType() {
        return GraphQLScalarType.newScalar()
                                .name("LocalDateTime")
                                .coercing(new LocalDateTimeCoercing())
                                .build();
    }
}
