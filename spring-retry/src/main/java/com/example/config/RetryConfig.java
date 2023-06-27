package com.example.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@Configuration
public class RetryConfig {
    @Bean
    public RetryTemplate retryTemplate1(RetryListenerImpl retryListener) {
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(createRetryPolicy());
        template.setBackOffPolicy(backOffPolicy);
        template.registerListener(retryListener);
        return template;
    }

    @Bean
    public RetryTemplate retryTemplate2(RetryListenerImpl retryListener) {
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(2000);
        backOffPolicy.setMultiplier(3.0);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(createRetryPolicy());
        template.setBackOffPolicy(backOffPolicy);
        template.registerListener(retryListener);
        return template;
    }

    private static SimpleRetryPolicy createRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> retryableExceptions =
                Map.of(ResourceAccessException.class, true,
                       HttpServerErrorException.class, true,
                       HttpClientErrorException.class, false);
        return new SimpleRetryPolicy(4, retryableExceptions);
    }
}
