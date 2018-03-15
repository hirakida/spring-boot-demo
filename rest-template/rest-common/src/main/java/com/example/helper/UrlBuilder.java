package com.example.helper;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UrlBuilder {

    public static final String API_URL = "https://api.github.com";

    public static String getUsersUrl(String userName) {
        return UriComponentsBuilder.fromHttpUrl(API_URL)
                                   .path("/users/{username}")
                                   .buildAndExpand(userName)
                                   .toUriString();
    }
}
