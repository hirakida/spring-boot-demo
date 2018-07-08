package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.AsyncRestTemplate;

@Configuration
public class AsyncRestTemplateConfig {

    @Bean
    public AsyncRestOperations asyncRestOperations() {
        AsyncRestTemplate template = new AsyncRestTemplate();
        template.setAsyncRequestFactory(httpComponentsAsyncClientHttpRequestFactory());
        return template;
    }

    @Bean
    public HttpComponentsAsyncClientHttpRequestFactory httpComponentsAsyncClientHttpRequestFactory() {
        HttpComponentsAsyncClientHttpRequestFactory factory = new HttpComponentsAsyncClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setConnectionRequestTimeout(5000);
        return factory;
    }
}
