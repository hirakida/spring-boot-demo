package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieMaxAge(60);
        serializer.setCookieName("SPRING_SESSION_REDIS");
        return serializer;
    }

    @Bean
    @SessionScope
    public AppSession appSession() {
        return new AppSession();
    }
}
