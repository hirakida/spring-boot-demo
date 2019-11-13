package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@RefreshScope
@Component
@ConfigurationProperties(prefix = "app")
@Validated
@Data
public class AppProperties {
    private @NotNull String message1;
    private @NotNull String message2;
    private @NotNull Integer number1;
    private @NotNull Integer number2;
}
