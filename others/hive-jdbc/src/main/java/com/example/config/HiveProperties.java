package com.example.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties("hive")
@Validated
@Data
public class HiveProperties {
    @NotNull
    private String url;
}
