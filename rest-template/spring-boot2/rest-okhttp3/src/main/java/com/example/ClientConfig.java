package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

@Configuration
public class ClientConfig {

    @Bean
    public ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setWriteTimeout(5000);
        return factory;
    }
}
