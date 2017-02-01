package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ROLE_ADMIN";
    private static final String USER = "ROLE_USER";

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースへのアクセスはセキュリティの設定を無視する
        web.ignoring()
           .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/admin/**").hasAnyAuthority(ADMIN)
            .anyRequest().authenticated();  // 認証されているユーザーのみアクセスできる

        http.formLogin()
            .defaultSuccessUrl("/mypage")
            .permitAll();

        http.logout()
//            .logoutUrl("/logout")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
            .logoutSuccessUrl("/");
    }

    // AuthenticationManagerの設定
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()   // in-memoryで認証情報を管理
            // authorities()の代わりにroles()を使うと自動でprefixにROLE_が付く
            .withUser("user").password("user").authorities(USER)
            .and()
            .withUser("admin").password("admin").authorities(USER, ADMIN);
    }
}
