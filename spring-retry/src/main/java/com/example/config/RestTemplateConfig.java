package com.example.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.DefaultUriTemplateHandler;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestOperations restOperations() {
        return new RestTemplateBuilder()
                .additionalCustomizers(restTemplateCustomizer())
                .build();
    }

    RestTemplateCustomizer restTemplateCustomizer() {
        return restTemplate -> {
            DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
            uriTemplateHandler.setStrictEncoding(true);
            new RestTemplateBuilder()
                    .defaultMessageConverters()
                    .uriTemplateHandler(uriTemplateHandler)
                    .setConnectTimeout(5000)
                    .setReadTimeout(5000)
                    .configure(restTemplate);
        };
    }
}
