package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "user-api")
@Data
public class UserApiProperties {
    private String scheme = "http";
    private String host = "localhost";
    private Integer port = 8080;

    public String getBaseUrl() {
        return UriComponentsBuilder.newInstance()
                                   .scheme(scheme)
                                   .host(host)
                                   .port(port)
                                   .build()
                                   .toUriString();
    }
}
