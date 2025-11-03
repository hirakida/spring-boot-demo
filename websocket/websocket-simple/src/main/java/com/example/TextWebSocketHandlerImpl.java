package com.example;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class TextWebSocketHandlerImpl extends TextWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextWebSocketHandlerImpl.class);
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("established: session={}", session);
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("closed: session={} status={}", session, status);
        sessions.remove(session.getId());
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        LOGGER.info("pongMessage: session={} message={}", session, message);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOGGER.info("handleTextMessage: session={} message={}", session, message);
        sendMessage(message);
    }

    private void sendMessage(TextMessage message) {
        sessions.forEach((id, session) -> {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                LOGGER.error("{}", e.getMessage(), e);
            }
        });
    }
}
