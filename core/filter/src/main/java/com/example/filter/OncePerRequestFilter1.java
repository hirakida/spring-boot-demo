package com.example.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class OncePerRequestFilter1 extends OncePerRequestFilter {

    @Override
    protected void initFilterBean() throws ServletException {
        log.info("initFilterBean");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal: {}", request.getRequestURI());
        // Call the next filter.
        // If you do not want to call the next filter, put return.
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
