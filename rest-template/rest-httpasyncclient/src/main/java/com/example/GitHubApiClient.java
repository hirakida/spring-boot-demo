package com.example;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.RestClientException;

import com.example.helper.UrlBuilder;
import com.example.model.GitHubUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GitHubApiClient {

    private final AsyncRestOperations asyncRestOperations;

    public GitHubUser getUsers(String userName) {
        String url = UrlBuilder.getUsersUrl(userName);
        ListenableFuture<ResponseEntity<GitHubUser>> response =
                asyncRestOperations.getForEntity(url, GitHubUser.class);
        try {
            ResponseEntity<GitHubUser> entity = response.get();
            return entity.getBody();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.toString());
            throw new RestClientException("error");
        }
    }
}
