package com.example;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished session={}", session);
        sessionMap.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("afterConnectionClosed session={} status={}", session, status);
        sessionMap.remove(session.getId());
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("handlePongMessage session={} message={}", session, message);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessage session={} message={}", session, message);
        sendMessage(message);
    }

    public void sendMessage(String message) {
        TextMessage textMessage = new TextMessage(message);
        sendMessage(textMessage);
    }

    private void sendMessage(TextMessage message) {
        sessionMap.forEach((key, value) -> {
            try {
                value.sendMessage(message);
                log.info("sendMessage {} {}", value, message);
            } catch (IOException e) {
                log.warn("sendMessage failed.", e);
            }
        });
    }
}
