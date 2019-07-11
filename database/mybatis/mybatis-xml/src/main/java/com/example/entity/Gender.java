package com.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE(0),
    FEMALE(1);

    @Getter
    private final int code;
}
