package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user-api")
public record UserApiProperties(String scheme, String host, int port) {
}
