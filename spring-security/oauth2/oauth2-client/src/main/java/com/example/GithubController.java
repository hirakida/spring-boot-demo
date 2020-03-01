package com.example;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.Repo;
import com.example.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GithubController {
    private final WebClient webClient;

    @GetMapping("/github/user")
    public Mono<User> getUser(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return webClient.get()
                        .uri("https://api.github.com/user")
                        .attributes(oauth2AuthorizedClient(client))
                        .retrieve()
                        .bodyToMono(User.class);
    }

    @GetMapping("/github/repos")
    public Flux<Repo> getRepos(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return webClient.get()
                        .uri("https://api.github.com/user/repos")
                        .attributes(oauth2AuthorizedClient(client))
                        .retrieve()
                        .bodyToFlux(Repo.class);
    }
}
