package com.example.web.enums;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender {
    MALE("m", "male"),
    FEMALE("f", "female");

    private final String code;
    private final String label;

    public static Optional<Gender> of(String code) {
        return Stream.of(values())
                     .filter(value -> value.getCode().equals(code))
                     .findFirst();
    }
}
