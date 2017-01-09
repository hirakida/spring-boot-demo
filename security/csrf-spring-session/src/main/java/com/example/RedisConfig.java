package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableRedisHttpSession
public class RedisConfig {

    // session cookieを変更する場合
    @Bean
    HttpSessionStrategy httpSessionStrategy() {
        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
        strategy.setCookieSerializer(cookieSerializer());
        return strategy;
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("SESSION_TEST");
        serializer.setCookieMaxAge(3600);   // 1 hour
//        serializer.setCookieMaxAge(-1);   // 省略またはマイナスを指定した場合は、ブラウザを閉じるまで
        return serializer;
    }
}
