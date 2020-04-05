package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class Application {

    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return RouterFunctions.route(GET("/users/{id}")
                                             .and(accept(APPLICATION_JSON)),
                                     userHandler::findById)
                              .andRoute(GET("/users")
                                                .and(accept(APPLICATION_JSON)),
                                        userHandler::findAll);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
