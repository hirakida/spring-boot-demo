package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@EnableMongoHttpSession
public class HttpSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieMaxAge(60);
        serializer.setCookieName("SPRING-SESSION-MONGODB");
        return serializer;
    }

    @Bean
    @SessionScope
    public AppSession appSession() {
        return new AppSession();
    }
}
