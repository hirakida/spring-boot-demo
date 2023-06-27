package com.example.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import graphql.GraphQLContext;
import graphql.Internal;
import graphql.execution.CoercedVariables;
import graphql.language.Value;
import graphql.schema.Coercing;

@Internal
public final class LocalDateTimeCoercing implements Coercing<LocalDateTime, String> {
    @Override
    public String serialize(Object input, GraphQLContext graphQLContext, Locale locale) {
        LocalDateTime dateTime = (LocalDateTime) input;
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime);
    }

    @Override
    public LocalDateTime parseValue(Object input, GraphQLContext graphQLContext, Locale locale) {
        return LocalDateTime.parse((CharSequence) input);
    }

    @Override
    public LocalDateTime parseLiteral(Value<?> input, CoercedVariables variables,
                                      GraphQLContext graphQLContext, Locale locale) {
        return LocalDateTime.parse((CharSequence) input);
    }
}
