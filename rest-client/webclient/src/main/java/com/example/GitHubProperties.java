package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "github")
@Validated
public record GitHubProperties(@NotNull String baseUrl) {
}
