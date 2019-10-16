package com.example.listener;

import java.time.Instant;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.info("sessionCreated: sessionId={} creationTime={} maxInactiveInterval={}",
                 session.getId(), Instant.ofEpochMilli(session.getCreationTime()),
                 session.getMaxInactiveInterval());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.info("sessionDestroyed: sessionId={} creationTime={} maxInactiveInterval={}",
                 session.getId(), Instant.ofEpochMilli(session.getCreationTime()),
                 session.getMaxInactiveInterval());
    }
}
