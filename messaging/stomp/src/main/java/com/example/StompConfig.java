package com.example;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("endpoint")
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
                .addInterceptors(new HandshakeInterceptorImpl());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        // queueまたはtopicを有効にする(両方可)。queueは1対1(P2P)、topicは1対多(Pub-Sub)
        registry.enableSimpleBroker("/topic");
    }

    public static class HandshakeInterceptorImpl implements HandshakeInterceptor {
        @Override
        public boolean beforeHandshake(ServerHttpRequest var1,
                                       ServerHttpResponse var2,
                                       WebSocketHandler var3,
                                       Map<String, Object> var4) throws Exception {
            log.info("beforeHandshake {}", var1.getURI());
            return true;
        }

        @Override
        public void afterHandshake(ServerHttpRequest var1,
                                   ServerHttpResponse var2,
                                   WebSocketHandler var3,
                                   Exception var4) {
            log.info("afterHandshake {}", var1.getURI());
        }
    }
}
