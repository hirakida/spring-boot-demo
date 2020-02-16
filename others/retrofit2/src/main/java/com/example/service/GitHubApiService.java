package com.example.service;

import com.example.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApiService {
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}
