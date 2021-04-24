package com.example.client;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.Repo;
import com.example.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GitHubClient {
    private final WebClient webClient;

    public Mono<User> getUser(OAuth2AuthorizedClient client) {
        return webClient.get()
                        .uri("/user")
                        .attributes(oauth2AuthorizedClient(client))
                        .retrieve()
                        .bodyToMono(User.class);
    }

    public Flux<Repo> getUserRepos(OAuth2AuthorizedClient client) {
        return webClient.get()
                        .uri("/user/repos")
                        .attributes(oauth2AuthorizedClient(client))
                        .retrieve()
                        .bodyToFlux(Repo.class);
    }
}
