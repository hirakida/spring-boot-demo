package com.example;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("established: session={}", session);
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("closed: session={} status={}", session, status);
        sessions.remove(session.getId());
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("pongMessage: session={} message={}", session, message);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessage: session={} message={}", session, message);
        sendMessage(message);
    }

    private void sendMessage(TextMessage message) {
        sessions.forEach((id, session) -> {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                log.error("{}", e.getMessage(), e);
            }
        });
    }
}
