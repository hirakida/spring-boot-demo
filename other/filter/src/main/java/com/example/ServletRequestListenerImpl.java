package com.example;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet標準のServletRequestListener
 */
@Component
@Slf4j
public class ServletRequestListenerImpl implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        log.info("requestInitialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        log.info("requestDestroyed");
    }
}
