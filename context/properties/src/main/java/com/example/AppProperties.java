package com.example;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties(prefix = "app")
@Validated
@Data
public class AppProperties {
    private @NotNull AppNumber number;
    private @NotNull AppString string;

    @Data
    public static class AppNumber {
        private @Min(1) int value1;
        private @Min(0) int value2;
    }

    @Data
    public static class AppString {
        private @NotEmpty String value1;
        private String value2;
    }
}
