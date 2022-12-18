package com.example;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/admin1/**").hasAnyRole(Role.ADMIN.name())
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserBuilder userBuilder = User.builder()
                                      .passwordEncoder(this::encodePassword);
        UserDetails guest = userBuilder
                .username("guest1").password("guest1").roles(Role.GUEST.name())
                .build();
        UserDetails user = userBuilder
                .username("user1").password("user1").roles(Role.USER.name())
                .build();
        UserDetails admin = userBuilder
                .username("admin1").password("admin1").roles(Role.USER.name(), Role.ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(guest, user, admin);
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
