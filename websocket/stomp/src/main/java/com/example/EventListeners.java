package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventListeners {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        log.info("BrokerAvailabilityEvent {}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionConnectEvent event) {
        log.info("SessionConnectEvent {}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionConnectedEvent event) {
        log.info("SessionConnectedEvent {}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionSubscribeEvent event) {
        log.info("SessionSubscribeEvent {}", event);
        sendMessage(event);
    }

    @EventListener
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        log.info("SessionUnsubscribeEvent {}", event);
    }

    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent event) {
        log.info("SessionDisconnectEvent {}", event);
    }

    private void sendMessage(AbstractSubProtocolEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String destination = headerAccessor.getDestination();
        SimpMessageType messageType = headerAccessor.getMessageType();

        if (StringUtils.hasText(destination) && messageType != null) {
            simpMessagingTemplate.convertAndSend(destination, messageType);
        }
    }
}
