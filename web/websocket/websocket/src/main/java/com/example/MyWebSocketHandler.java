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
    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("established: session={}", session);
        sessionMap.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("closed: session={} status={}", session, status);
        sessionMap.remove(session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessage: session={} message={}", session, message);
        sessionMap.forEach((id, webSocketSession) -> {
            try {
                webSocketSession.sendMessage(message);
            } catch (IOException e) {
                log.error(e.toString());
            }
        });
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("pongMessage: session={} message={}", session, message);
    }
}
