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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * CSRF対策はデフォルトで適用される
 * デフォルトではPOST, PUT, DELETE, PATCH methodのrequestに対してCSRF tokenのチェックが行われる
 * CsrfFilter.DEFAULT_CSRF_MATCHER
 * CSRF tokenはsession単位に生成される
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースへのアクセスはセキュリティの設定を無視する
        web.ignoring()
           .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .csrf()
            .csrfTokenRepository(new CookieCsrfTokenRepository())
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
                log.error("error", e);
                super.handle(request, response, e);
            }
        };
    }
}
