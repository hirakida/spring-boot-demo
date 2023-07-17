package com.example.config;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CustomRequestLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void initFilterBean() throws ServletException {
        log.info("initFilterBean");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal before: {}", request.getRequestURI());
        filterChain.doFilter(request, response);
        log.info("doFilterInternal after: {}", request.getRequestURI());
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
