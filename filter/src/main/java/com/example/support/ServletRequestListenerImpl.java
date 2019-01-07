package com.example.support;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServletRequestListenerImpl implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        log.info("requestInitialized: {}", event);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        log.info("requestDestroyed: {}", event);
    }
}
