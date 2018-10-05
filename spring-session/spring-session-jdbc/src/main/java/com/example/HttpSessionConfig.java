package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@EnableJdbcHttpSession
public class HttpSessionConfig {

    @Bean
    @SessionScope
    public SessionBean sessionBean() {
        return new SessionBean();
    }
}
