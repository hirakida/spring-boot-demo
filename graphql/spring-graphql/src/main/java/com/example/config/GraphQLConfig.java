package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType scalarType = GraphQLScalarType.newScalar()
                                                        .name("LocalDateTime")
                                                        .coercing(new LocalDateTimeCoercing())
                                                        .build();
        return builder -> builder.scalar(scalarType);
    }
}
