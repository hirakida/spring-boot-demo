package com.example.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
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
            .antMatchers("/admin1/**").hasAnyRole(Role.ADMIN.name())
            .anyRequest().authenticated();
        http.formLogin()
            .defaultSuccessUrl("/")
            .permitAll();
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
            .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("guest1").password(encodePassword("guest1")).roles(Role.GUEST.name())
            .and()
            .withUser("user1").password(encodePassword("user1")).roles(Role.USER.name())
            .and()
            .withUser("user2").password(encodePassword("user2")).roles(Role.USER.name())
            .and()
            .withUser("admin1").password(encodePassword("admin1")).roles(Role.USER.name(), Role.ADMIN.name());
    }

    @Bean
    public RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler(HttpServletResponse.SC_FORBIDDEN);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String encodePassword(String password) {
        return passwordEncoder().encode(password);
    }
}
