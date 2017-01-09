package com.example;

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

@Slf4j
public class CustomFilter {

    @Component
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class RequestFilter1 extends OncePerRequestFilter {
        // アプリケーション起動時に呼び出される
        @Override
        protected void initFilterBean() throws ServletException {
            log.info("RequestFilter1::initFilterBean");
        }

        // ServletRequestListenerの後でInterceptorの前に呼び出される
        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            log.info("RequestFilter1::doFilterInternal");
            // 後続処理(次のFilter又はServlet)を呼び出す
            // 後続処理を呼び出したくない場合は、このタイミングでreturnする
            filterChain.doFilter(request, response);
        }

        // アプリケーション終了時に呼び出される
        @Override
        public void destroy() {
            log.info("RequestFilter1::destroy");
        }
    }

    @Component
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public static class RequestFilter2 extends OncePerRequestFilter {
        @Override
        protected void initFilterBean() throws ServletException {
            log.info("RequestFilter2::initFilterBean");
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            log.info("RequestFilter2::doFilterInternal");
            filterChain.doFilter(request, response);
        }

        @Override
        public void destroy() {
            log.info("RequestFilter2::destroy");
        }
    }
}
