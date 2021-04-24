package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .csrf()
            .csrfTokenRepository(new CookieCsrfTokenRepository())
//            .requireCsrfProtectionMatcher(CsrfFilter.DEFAULT_CSRF_MATCHER)
//            .ignoringAntMatchers("/xxxx")
            .and()
            .addFilterBefore(new MyRequestFilter(), CsrfFilter.class)
            .exceptionHandling()
            .accessDeniedHandler(new MyAccessDeniedHandler());
    }
}
