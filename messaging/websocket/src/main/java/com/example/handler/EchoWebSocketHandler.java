package com.example.handler;

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
public class EchoWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessionPool = new ConcurrentHashMap<>();

    /**
     * 接続が確立した後で呼ばれる
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("EchoWebSocketHandler::afterConnectionEstablished {}", session);
        sessionPool.put(session.getId(), session);
    }

    /**
     * 切断後に呼ばれる
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) throws Exception {
        log.info("EchoWebSocketHandler::afterConnectionClosed {} {}", session, status);
        sessionPool.remove(session.getId());
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("EchoWebSocketHandler::handlePongMessage {} {} {}", session, message);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("EchoWebSocketHandler::handleTextMessage {} {}", session, message);

        sessionPool.forEach((key, value) -> {
            try {
                value.sendMessage(message);
                log.info("{} {}", value, message);
            } catch (IOException e) {
                log.warn("sendMessage failed.", e);
            }
        });
    }
}
