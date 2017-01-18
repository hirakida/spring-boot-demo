package com.example;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE("male", "男性"),
    FEMALE("female", "女性");

    private final String code;
    @Getter
    private final String label;

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Gender create(String code) {
        return of(code)
                .orElse(null);
    }

    public static Optional<Gender> of(String code) {
        return Stream.of(values())
                     .filter(x -> StringUtils.equals(x.getCode(), code))
                     .findFirst();
    }
}
