package com.example.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .csrf()
            .csrfTokenRepository(new CookieCsrfTokenRepository())
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new AccessDeniedHandlerImpl() {
                @Override
                public void handle(HttpServletRequest request,
                                   HttpServletResponse response,
                                   AccessDeniedException e) throws IOException, ServletException {
                    log.error("{}", e.getMessage());
                    super.handle(request, response, e);
                }
            });
    }
}
