package com.example.config;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.example.support.ApiResponseErrorHandler;
import com.example.support.ClientHttpRequestInterceptorImpl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setConnectionRequestTimeout(5000);
        return restTemplateBuilder
                .requestFactory(() -> factory)
                .errorHandler(new ApiResponseErrorHandler())
                .interceptors(List.of(new ClientHttpRequestInterceptorImpl()))
                .build();
    }

    @Bean
    @Primary
    public RestOperations okHttpRestOperations(RestTemplateBuilder restTemplateBuilder) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY))
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
        return restTemplateBuilder
                .requestFactory(() -> new OkHttp3ClientHttpRequestFactory(okHttpClient))
                .errorHandler(new ApiResponseErrorHandler())
                .interceptors(List.of(new ClientHttpRequestInterceptorImpl()))
                .build();
    }

    //    @Bean
    public RestTemplateCustomizerImpl restTemplateCustomizer() {
        return new RestTemplateCustomizerImpl();
    }

    private static class RestTemplateCustomizerImpl implements RestTemplateCustomizer {
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
}
