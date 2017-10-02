package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * CSRF tokenはsession単位に生成される
     * sessionはtomcatのStandardSessionが使用される
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().permitAll()
//            .and()
//            .csrf()   // HttpSessionCsrfTokenRepository is used
//            .requireCsrfProtectionMatcher(CsrfFilter.DEFAULT_CSRF_MATCHER)
//            .ignoringAntMatchers("/public")
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler());
    }

    private static AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl() {
            @Override
            public void handle(HttpServletRequest request,
                               HttpServletResponse response,
                               AccessDeniedException e) throws IOException, ServletException {
                if (e instanceof InvalidCsrfTokenException) {
                    log.error("invalid csrf token.", e);
                } else if (e instanceof MissingCsrfTokenException) {
                    log.error("missing csrf token.", e);
                } else {
                    log.error(e.toString());
                }
                super.handle(request, response, e);
            }
        };
    }
}
