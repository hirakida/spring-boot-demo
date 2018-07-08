package com.example.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder,
                                         ClientHttpRequestFactory clientHttpRequestFactory) {
        return restTemplateBuilder
                .requestFactory(() -> clientHttpRequestFactory)
                .errorHandler(new ApiResponseErrorHandler())
                .build();
    }
}
