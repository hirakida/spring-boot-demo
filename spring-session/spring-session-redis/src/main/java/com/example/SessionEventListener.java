package com.example;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.context.event.EventListener;
import org.springframework.session.events.AbstractSessionEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SessionEventListener {

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
        log.info("{}: sessionId={} creationTime={} isExpired={} maxInactiveInterval={}",
                 name, event.getSessionId(),
                 LocalDateTime.ofInstant(event.getSession().getCreationTime(), ZoneId.systemDefault()),
                 event.getSession().isExpired(),
                 event.getSession().getMaxInactiveInterval().getSeconds());
    }
}
