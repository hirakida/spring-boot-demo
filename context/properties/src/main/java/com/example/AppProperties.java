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
    @NotNull
    private NumberData number;
    @NotNull
    private StringData string;
    @NotNull
    private String ddlAuto;

    @Data
    public static class NumberData {
        /**
         * Min(1).
         */
        @Min(1)
        private long value1 = 1;
        /**
         * Min(0).
         */
        @Min(0)
        private long value2;
    }

    @Data
    public static class StringData {
        /**
         * NotEmpty.
         */
        @NotEmpty
        private String value1;
        /**
         * Nullable.
         */
        private String value2;
    }
}
