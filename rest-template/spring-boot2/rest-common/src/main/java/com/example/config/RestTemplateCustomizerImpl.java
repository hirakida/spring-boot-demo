package com.example.config;

import static java.util.Collections.singletonList;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateCustomizerImpl implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        new RestTemplateBuilder()
                .defaultMessageConverters()
                .detectRequestFactory(false)
                .errorHandler(new ApiResponseErrorHandler())
                .interceptors(singletonList(new ClientHttpRequestInterceptorImpl()))
                .setConnectTimeout(5000)
                .setReadTimeout(5000)
                .configure(restTemplate);
    }
}
