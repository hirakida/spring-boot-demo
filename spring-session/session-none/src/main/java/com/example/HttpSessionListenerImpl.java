package com.example;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpSessionListenerImpl implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("sessionCreated: sessionId={}", se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("sessionDestroyed: sessionId={}", se.getSession().getId());
    }
}
