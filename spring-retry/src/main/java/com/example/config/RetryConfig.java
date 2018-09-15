package com.example.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@EnableRetry
@Configuration
public class RetryConfig {

    private static final int MAX_ATTEMPTS = 4;
    private static final int BACK_OFF_PERIOD = 500;

    @Bean
    public RetryTemplate retryTemplate() {
        Map<Class<? extends Throwable>, Boolean> exceptions = new HashMap<>();
        exceptions.put(ResourceAccessException.class, true);
        exceptions.put(HttpServerErrorException.class, false);
        exceptions.put(HttpClientErrorException.class, false);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(MAX_ATTEMPTS, exceptions);
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(BACK_OFF_PERIOD);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);
        template.registerListener(retryListener());
        return template;
    }

    @Bean
    public RetryListenerImpl retryListener() {
        return new RetryListenerImpl();
    }

    @Bean
    public ExceptionChecker exceptionChecker() {
        return new ExceptionChecker();
    }
}
