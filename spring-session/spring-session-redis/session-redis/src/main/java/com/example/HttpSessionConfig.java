package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Bean
    @SessionScope
    public AppSession appSession() {
        return new AppSession();
    }
}
