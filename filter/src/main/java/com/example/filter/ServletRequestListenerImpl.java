package com.example.filter;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServletRequestListenerImpl implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        log.info("requestInitialized {}", event);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        log.info("requestDestroyed {}", event);
    }
}
