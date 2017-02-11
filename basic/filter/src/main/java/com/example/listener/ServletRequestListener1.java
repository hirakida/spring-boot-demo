package com.example.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet標準のServletRequestListener
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ServletRequestListener1 implements ServletRequestListener {
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
