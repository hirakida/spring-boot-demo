package com.example.domain;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE,
    FEMALE;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static Gender fromName(String name) {
        return Stream.of(values())
                     .filter(x -> x.name().equals(name))
                     .findFirst()
                     .orElse(null);
    }
}
