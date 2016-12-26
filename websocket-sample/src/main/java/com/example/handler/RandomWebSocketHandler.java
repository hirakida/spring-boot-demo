package com.example.handler;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RandomWebSocketHandler extends AbstractWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("RandomWebSocketHandler::afterConnectionEstablished {}", session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) throws Exception {
        log.info("RandomWebSocketHandler::afterConnectionClosed {} {}", session, status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // ひとまずランダムな文字列を返す
        String text = RandomStringUtils.randomAlphabetic(10);
        session.sendMessage(new TextMessage(text));
        log.info("RandomWebSocketHandler::handleTextMessage {} {} {}", session, message, text);
    }
}
