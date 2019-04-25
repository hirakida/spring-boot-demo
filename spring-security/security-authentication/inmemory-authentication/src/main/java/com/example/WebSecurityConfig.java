package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/admin/**").hasAnyRole(Role.ADMIN.name())
            .anyRequest().authenticated();

        http.formLogin()
            .defaultSuccessUrl("/home")
            .permitAll();

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
            .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("guest1").password(password("guest1")).roles(Role.GUEST.name())
            .and()
            .withUser("user1").password(password("user1")).roles(Role.USER.name())
            .and()
            .withUser("user2").password(password("user2")).roles(Role.USER.name())
            .and()
            .withUser("admin1").password(password("admin1")).roles(Role.USER.name(), Role.ADMIN.name());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String password(String password) {
        return passwordEncoder().encode(password);
    }

    public enum Role {
        GUEST,
        USER,
        ADMIN
    }
}
