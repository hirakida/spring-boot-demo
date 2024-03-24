package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
    @Bean
    public GitHubService gitHubService(GitHubProperties properties) {
        WebClient client = WebClient.builder()
                                    .baseUrl(properties.baseUrl())
                                    .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client))
                                                                 .build();
        return factory.createClient(GitHubService.class);
    }
}
