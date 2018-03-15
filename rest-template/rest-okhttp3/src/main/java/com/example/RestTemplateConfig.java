package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;

import com.example.config.RestTemplateCustomizerImpl;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Bean
    public RestOperations okHttp3RestOperations() {
        return new RestTemplateBuilder()
                .additionalCustomizers(new RestTemplateCustomizerImpl())
                .requestFactory(okHttp3ClientHttpRequestFactory())
                .build();
    }

    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setWriteTimeout(5000);
        return factory;
    }
}
