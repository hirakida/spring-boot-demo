package com.example.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ServletRequestListener2 implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent var1) {
        log.info("ServletRequestListener2::requestInitialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent var1) {
        log.info("ServletRequestListener2::requestDestroyed");
    }
}
