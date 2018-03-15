package com.example.enums;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Blood {
    UNKNOWN("unknown", "Unknown"),
    A("a", "A"),
    B("b", "B"),
    O("o", "O"),
    AB("ab", "AB");

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
