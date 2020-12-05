package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties(prefix = "github")
@Validated
@Data
public class GitHubProperties {
    @NotNull
    private String baseUrl;
}
