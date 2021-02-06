package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.schema.Coercing;
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

    private static class LocalDateTimeCoercing implements Coercing<LocalDateTime, String> {
        @Override
        public String serialize(Object input) {
            LocalDateTime dateTime = (LocalDateTime) input;
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime);
        }

        @Override
        public LocalDateTime parseValue(Object input) {
            return LocalDateTime.parse((CharSequence) input);
        }

        @Override
        public LocalDateTime parseLiteral(Object input) {
            return LocalDateTime.parse((CharSequence) input);
        }
    }
}
