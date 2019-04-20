package com.example.web.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class OncePerRequestFilterExt extends OncePerRequestFilter {

    @Override
    protected void initFilterBean() throws ServletException {
        log.info("initFilterBean");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal: {}", request.getRequestURI());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
