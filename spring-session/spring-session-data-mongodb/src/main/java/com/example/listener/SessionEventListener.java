package com.example.listener;

import org.springframework.context.event.EventListener;
import org.springframework.session.Session;
import org.springframework.session.events.AbstractSessionEvent;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SessionEventListener {

    @EventListener
    public void onSessionCreatedEvent(SessionCreatedEvent event) {
        log("SessionCreatedEvent", event);
    }

    @EventListener
    public void onSessionDeletedEvent(SessionDeletedEvent event) {
        log("SessionDeletedEvent", event);
    }

    @EventListener
    public void onSessionDestroyedEvent(SessionDestroyedEvent event) {
        log("SessionDestroyedEvent", event);
    }

    @EventListener
    public void onSessionExpiredEvent(SessionExpiredEvent event) {
        log("SessionExpiredEvent", event);
    }

    private static void log(String name, AbstractSessionEvent event) {
        Session session = event.getSession();
        log.info("{}: sessionId={} creationTime={} isExpired={} maxInactiveInterval={}",
                 name, event.getSessionId(), session.getCreationTime(),
                 session.isExpired(), session.getMaxInactiveInterval().getSeconds());
    }
}
