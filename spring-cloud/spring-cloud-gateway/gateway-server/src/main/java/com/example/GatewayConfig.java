package com.example;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {
    private static final String SERVER1 = "http://localhost:8081";
    private static final String SERVER2 = "http://localhost:8082";

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/").and().query("limiter")
                             .filters(f -> f.setPath("/datetime")
                                            .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())
                                                                      .setKeyResolver(keyResolver())))
                             .uri(SERVER1))
                .route(r -> r.path("/datetime")
                             .filters(f -> f.addResponseHeader("X-DateTime",
                                                               LocalDateTime.now().toString()))
                             .uri(SERVER1))
                .route(r -> r.path("/md5/{text}")
                             .uri(SERVER2))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(3, 5);
    }

    @Bean
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest()
                                                                    .getQueryParams()
                                                                    .getFirst("limiter")));
    }
}
