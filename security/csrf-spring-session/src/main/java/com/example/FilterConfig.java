package com.example;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean1() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new RequestLoggingFilter());
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return registrationBean;
    }

    @NoArgsConstructor
    public static class RequestLoggingFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                log.info("cookies");
                Arrays.asList(cookies)
                      .forEach(cookie -> log.info("name:{} value:{} secure:{} domain:{} path:{} maxAge:{}",
                                                  cookie.getName(), cookie.getValue(), cookie.getSecure(),
                                                  cookie.getDomain(), cookie.getPath(), cookie.getMaxAge()));
            }
            filterChain.doFilter(request, response);
        }
    }
}
