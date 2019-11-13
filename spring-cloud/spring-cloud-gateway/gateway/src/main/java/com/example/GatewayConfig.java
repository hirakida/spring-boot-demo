package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                      .route(r -> r.path("/datetime")
                                   .filters(f -> f.addResponseHeader("X-DateTime",
                                                                     LocalDateTime.now().toString()))
                                   .uri("http://localhost:8081"))
                      .route(r -> r.path("/date")
                                   .filters(f -> f.addResponseHeader("X-Date",
                                                                     LocalDate.now().toString()))
                                   .uri("http://localhost:8081"))
                      .route(r -> r.path("/md5").and().query("text")
                                   .uri("http://localhost:8082"))
                      .route(r -> r.path("/sha1").and().query("text")
                                   .uri("http://localhost:8082"))
                      .build();
    }
}
