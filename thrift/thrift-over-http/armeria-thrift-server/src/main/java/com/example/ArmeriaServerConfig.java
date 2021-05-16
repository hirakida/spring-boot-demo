package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello.Hello;

import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.thrift.THttpService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;

@Configuration
public class ArmeriaServerConfig {
    @Bean
    public ArmeriaServerConfigurator armeriaServerConfigurator(Hello.AsyncIface helloHandler) {
        return builder -> builder.route()
                                 .path("/hello")
                                 .defaultServiceName("Hello")
                                 .decorator(LoggingService.newDecorator())
                                 .build(THttpService.of(helloHandler));
    }
}
