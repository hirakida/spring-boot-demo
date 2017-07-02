package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ignore access to static resources
        web.ignoring()
           .antMatchers("/webjars/**");
    }

    /**
     * CSRF対策はデフォルトでPOST, PUT, DELETE, PATCH methodのrequestに対して適用される
     * (CsrfFilter.DEFAULT_CSRF_MATCHER)
     * CSRF tokenはsession単位に生成される
     * sessionはtomcatのStandardSessionが使用される
     * csrfTokenRepositoryはHttpSessionCsrfTokenRepositoryが使用される
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
        // @formatter:on
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl() {
            @Override
            public void handle(HttpServletRequest request,
                               HttpServletResponse response,
                               AccessDeniedException e) throws IOException,
                                                               ServletException {
                // InvalidCsrfTokenExceptionはtokenが一致しなかった場合
                // MissingCsrfTokenExceptionはサーバーにtokenが保存されていなかった場合
                log.error("AccessDeniedHandler", e);
                super.handle(request, response, e);
            }
        };
    }
}
