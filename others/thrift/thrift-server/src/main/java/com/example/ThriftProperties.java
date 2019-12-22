package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties(prefix = "thrift")
@Validated
@Data
public class ThriftProperties {
    private @NotNull String host;
    private @NotNull Integer port;
    private @NotNull SSL ssl;

    @Data
    public static class SSL {
        private @NotNull boolean enabled;
        private @NotNull String keyStore;
        private @NotNull String keyStorePassword;
    }
}
