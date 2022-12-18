package com.example;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRequestFilter extends OncePerRequestFilter {
    private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!CsrfFilter.DEFAULT_CSRF_MATCHER.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Stream.of(cookies)
                  .filter(cookie -> CSRF_COOKIE_NAME.equals(cookie.getName()))
                  .forEach(cookie -> log.info("name:{} value:{} secure:{} domain:{} path:{} maxAge:{}",
                                              cookie.getName(), cookie.getValue(), cookie.getSecure(),
                                              cookie.getDomain(), cookie.getPath(), cookie.getMaxAge()));
        }
        filterChain.doFilter(request, response);
    }
}
