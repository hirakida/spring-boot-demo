package com.example.userdetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    NO_AUTHORITIES(""),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;
}
