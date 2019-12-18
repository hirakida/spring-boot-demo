package com.example;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class Server2Application {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/md5/{text}"), Server2Application::md5);
    }

    private static Mono<ServerResponse> md5(ServerRequest request) {
        String text = request.pathVariable("text");
        return ok().bodyValue(DigestUtils.md5Hex(text));
    }

    public static void main(String[] args) {
        SpringApplication.run(Server2Application.class, args);
    }
}
