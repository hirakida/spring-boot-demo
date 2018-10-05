package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.web.context.annotation.SessionScope;

import com.example.bean.SessionBean;

@Configuration
@EnableMongoHttpSession(maxInactiveIntervalInSeconds = 60)
public class HttpSessionConfig {

    @Bean
    @SessionScope
    public SessionBean sessionBean() {
        return new SessionBean();
    }
}
