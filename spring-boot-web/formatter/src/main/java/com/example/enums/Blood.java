package com.example.enums;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Blood {
    UNKNOWN("unknown", "不明"),
    A("a", "A型"),
    B("b", "B型"),
    O("o", "O型"),
    AB("ab", "AB型");

    @Getter
    private final String code;
    @Getter
    private final String label;

    public static Optional<Blood> of(String code) {
        return Stream.of(values())
                     .filter(value -> value.getCode().equals(code))
                     .findFirst();
    }
}
