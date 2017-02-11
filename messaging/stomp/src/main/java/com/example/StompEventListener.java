package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StompEventListener {

    @EventListener
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        log.info("BrokerAvailabilityEvent={}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionConnectEvent event) {
        log.info("SessionConnectEvent={}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionConnectedEvent event) {
        log.info("SessionConnectedEvent={}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionSubscribeEvent event) {
        log.info("SessionSubscribeEvent={}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        log.info("SessionUnsubscribeEvent={}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent event) {
        log.info("SessionDisconnectEvent={}", event);
    }
}
