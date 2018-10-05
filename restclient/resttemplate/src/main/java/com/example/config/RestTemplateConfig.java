package com.example.config;

import java.util.Collections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;

import com.example.support.ApiResponseErrorHandler;
import com.example.support.ClientHttpRequestInterceptorImpl;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(this::httpComponentsClientHttpRequestFactory)
                .errorHandler(new ApiResponseErrorHandler())
                .interceptors(Collections.singletonList(new ClientHttpRequestInterceptorImpl()))
                .build();
    }

    public ClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setConnectionRequestTimeout(5000);
        return factory;
    }

    public ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setWriteTimeout(5000);
        return factory;
    }
}
