package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 特定のリクエストに対してセキュリティ設定を無視するなど、全体に関わる設定
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースへのアクセスはセキュリティの設定を無視する
        web.ignoring()
           .antMatchers("/webjars/**");
    }

    // 認可の設定や、login/logoutに関する設定をする
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認可の設定
        // 認証なしでアクセスできるページを指定
        http.authorizeRequests()
            .antMatchers("/", "/login").permitAll()
            .anyRequest().authenticated();

        // login
        http.formLogin()
            .loginProcessingUrl("/login_processing")
            .loginPage("/login")
            .usernameParameter("username").passwordParameter("password")
            .failureUrl("/login?error")
            .defaultSuccessUrl("/logged_in", true);

        // logout
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
            .logoutSuccessUrl("/");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
