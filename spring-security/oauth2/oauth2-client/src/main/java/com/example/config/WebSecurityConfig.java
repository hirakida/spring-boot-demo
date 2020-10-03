package com.example.config;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(registry -> registry.antMatchers("/", "/error").permitAll()
                                                   .anyRequest().authenticated())
            .exceptionHandling(configurer -> configurer.authenticationEntryPoint(
                    new HttpStatusEntryPoint(UNAUTHORIZED)))
            .oauth2Login()
            .defaultSuccessUrl("/success")
            .and()
            .logout()
            .logoutSuccessUrl("/");
    }
}
