package com.example.config;

import java.util.Collections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

import com.example.support.ApiResponseErrorHandler;
import com.example.support.ClientHttpRequestInterceptorImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateCustomizerImpl implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        new RestTemplateBuilder()
                .defaultMessageConverters()
                .detectRequestFactory(false)
                .errorHandler(new ApiResponseErrorHandler())
                .interceptors(Collections.singletonList(new ClientHttpRequestInterceptorImpl()))
                .configure(restTemplate);
    }
}
