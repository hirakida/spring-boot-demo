package com.example.scalars;

import graphql.PublicApi;
import graphql.schema.GraphQLScalarType;

@PublicApi
public final class ExtendedScalars {
    public static final GraphQLScalarType LOCAL_DATE_TIME =
            GraphQLScalarType.newScalar()
                             .name("LocalDateTime")
                             .coercing(new LocalDateTimeCoercing())
                             .build();

    private ExtendedScalars() {}
}
