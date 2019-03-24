package com.example.web;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    NO_AUTHORITIES(""),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    public List<GrantedAuthority> toGrantedAuthority() {
        if (this == USER) {
            return AuthorityUtils.createAuthorityList(USER.getRole());
        } else if (this == ADMIN) {
            return AuthorityUtils.createAuthorityList(ADMIN.getRole());
        } else {
            return AuthorityUtils.NO_AUTHORITIES;
        }
    }
}
