package com.example;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.google.common.collect.ImmutableMap;

import lombok.extern.slf4j.Slf4j;

@EnableRetry
@Configuration
@Slf4j
public class RetryConfig {

    private static final int MAX_ATTEMPTS = 4;

    //    @Bean
    public RetryTemplate retryTemplate() {

        Map<Class<? extends Throwable>, Boolean> exceptions =
                ImmutableMap.of(IllegalArgumentException.class, true,
                                IllegalStateException.class, true);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(MAX_ATTEMPTS, exceptions);
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(500);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);
        return template;
    }
}
