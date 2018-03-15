package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;

import com.example.config.RestTemplateCustomizerImpl;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestOperations okHttpRestOperations() {
        return new RestTemplateBuilder()
                .additionalCustomizers(new RestTemplateCustomizerImpl())
                .requestFactory(okHttpClientHttpRequestFactory())
                .build();
    }

    public OkHttpClientHttpRequestFactory okHttpClientHttpRequestFactory() {
        OkHttpClientHttpRequestFactory factory = new OkHttpClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setWriteTimeout(5000);
        return factory;
    }
}
