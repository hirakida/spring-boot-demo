package com.example.service;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.springframework.stereotype.Service;

import com.example.model.User;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

@Service
@RequiredArgsConstructor
public class GitHubService {
    private final GitHubApiService gitHubApiService;

    public User getUser(String username) {
        Response<User> response;
        try {
            response = gitHubApiService.getUser(username).execute();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return response.body();
    }
}
