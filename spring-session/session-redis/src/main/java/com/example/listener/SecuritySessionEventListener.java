package com.example.listener;

import org.springframework.context.event.EventListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecuritySessionEventListener {
    @EventListener
    public void onHttpSessionCreatedEvent(HttpSessionCreatedEvent event) {
        log.info("HttpSessionCreatedEvent: sessionId={}", event.getSession().getId());
    }

    @EventListener
    public void onHttpSessionDestroyedEvent(HttpSessionDestroyedEvent event) {
        log.info("HttpSessionDestroyedEvent: sessionId={}", event.getSession().getId());
    }
}
