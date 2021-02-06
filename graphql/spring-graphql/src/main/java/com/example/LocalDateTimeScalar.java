package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalDateTimeScalar {
    public static final GraphQLScalarType SCALAR_TYPE = GraphQLScalarType.newScalar()
                                                                         .name("LocalDateTime")
                                                                         .coercing(new LocalDateTimeCoercing())
                                                                         .build();

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
