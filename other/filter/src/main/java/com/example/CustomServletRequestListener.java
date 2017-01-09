package com.example;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet標準のServletRequestListener
 */
@Slf4j
public class CustomServletRequestListener {

    @Component
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class ServletRequestListener1 implements ServletRequestListener {
        // リクエスト開始時に呼ばれる
        // この順番に呼ばれる
        // 1. requestInitialized
        // 2. RequestFilter1::doFilterInternal
        // 3. Interceptor::preHandle
        @Override
        public void requestInitialized(ServletRequestEvent var1) {
            log.info("ServletRequestListener1::requestInitialized");
        }

        // リクエスト終了時に呼ばれる
        // 1. Interceptor::postHandle
        // 2. Interceptor::afterCompletion
        // 3. requestDestroyed
        @Override
        public void requestDestroyed(ServletRequestEvent var1) {
            log.info("ServletRequestListener1::requestDestroyed");
        }
    }

    @Component
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public static class ServletRequestListener2 implements ServletRequestListener {
        @Override
        public void requestInitialized(ServletRequestEvent var1) {
            log.info("ServletRequestListener2::requestInitialized");
        }

        @Override
        public void requestDestroyed(ServletRequestEvent var1) {
            log.info("ServletRequestListener2::requestDestroyed");
        }
    }
}
