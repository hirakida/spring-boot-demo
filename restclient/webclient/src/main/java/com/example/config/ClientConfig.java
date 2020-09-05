package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder,
                               GitHubProperties properties) {
        return builder.baseUrl(properties.getBaseUrl()).build();
    }
}
