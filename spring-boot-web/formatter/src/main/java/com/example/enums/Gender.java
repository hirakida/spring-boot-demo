package com.example.enums;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE("male", "男性"),
    FEMALE("female", "女性");

    @Getter
    private final String code;
    @Getter
    private final String label;

    public static Optional<Gender> of(String code) {
        return Stream.of(values())
                     .filter(value -> value.getCode().equals(code))
                     .findFirst();
    }
}
