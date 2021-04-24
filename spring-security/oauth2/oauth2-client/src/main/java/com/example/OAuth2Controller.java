package com.example;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.client.GitHubClient;
import com.example.model.Repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OAuth2Controller {
    private final GitHubClient client;

    @GetMapping("/success")
    public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User,
                        Model model) {
        log.info("{}", authorizedClient.getClientRegistration());
        log.info("{}", oauth2User.getName());
        log.info("{}", oauth2User.getAuthorities());

        List<Repo> repos = client.getUserRepos(authorizedClient)
                                 .collectList()
                                 .block();
        log.info("{}", repos);

        model.addAttribute("userAttributes", oauth2User.getAttributes());
        return "index";
    }
}
