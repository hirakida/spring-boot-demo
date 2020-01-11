package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.GitHubApiService;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Bean
    public GitHubApiService gitHubApiService(ObjectMapper objectMapper) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        return retrofit.create(GitHubApiService.class);
    }
}
