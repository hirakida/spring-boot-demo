package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestOperations restOperations() {
        return new RestTemplateBuilder()
                .additionalCustomizers(new RestTemplateCustomizerImpl())
                .build();
    }

    public static class RestTemplateCustomizerImpl implements RestTemplateCustomizer {
        @Override
        public void customize(RestTemplate restTemplate) {
            DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
            uriTemplateHandler.setStrictEncoding(true);
            new RestTemplateBuilder()
                    .defaultMessageConverters()
                    .uriTemplateHandler(uriTemplateHandler)
                    .setConnectTimeout(5000)
                    .setReadTimeout(5000)
                    .configure(restTemplate);
        }
    }
}
