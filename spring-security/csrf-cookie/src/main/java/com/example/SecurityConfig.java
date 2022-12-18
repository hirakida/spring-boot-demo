package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                   .csrf(csrf -> csrf.csrfTokenRepository(new CookieCsrfTokenRepository())
                                     .and()
                                     .addFilterBefore(new MyRequestFilter(), CsrfFilter.class))
                   .exceptionHandling(exceptionHandling -> exceptionHandling
                           .accessDeniedHandler(new MyAccessDeniedHandler()))
                   .build();
    }

    private static class MyAccessDeniedHandler extends AccessDeniedHandlerImpl {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
                throws IOException, ServletException {
            log.error("{}", e.getMessage(), e);
            super.handle(request, response, e);
        }
    }
}
