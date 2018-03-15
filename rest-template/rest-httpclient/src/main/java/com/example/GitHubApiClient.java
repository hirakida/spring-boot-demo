package com.example;

import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.helper.UrlBuilder;
import com.example.model.GitHubUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GitHubApiClient {

    private final RestOperations restOperations;

    public GitHubUser getUsers(String userName) {
        return restOperations.getForObject(UrlBuilder.getUsersUrl(userName), GitHubUser.class);
    }

    public Resource getUsersWithResource(String userName) {
        String url = UrlBuilder.getUsersUrl(userName);
        ResponseEntity<Resource> response = restOperations.exchange(url, HttpMethod.GET,
                                                                    HttpEntity.EMPTY, Resource.class);
        return response.getBody();
    }

    public Set<HttpMethod> options() {
        String url = UriComponentsBuilder.fromHttpUrl(UrlBuilder.API_URL)
                                         .toUriString();
        return restOperations.optionsForAllow(url);
    }
}
