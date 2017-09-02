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

    public static final String ENDPOINT_PATH = "endpoint";
    public static final String APP_DESTINATION_PREFIXES = "/app";
    public static final String TOPIC_DESTINATION_PREFIXES = "/topic";

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(ENDPOINT_PATH)
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
                .addInterceptors(new HandshakeInterceptorImpl());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(APP_DESTINATION_PREFIXES);
        registry.enableSimpleBroker(TOPIC_DESTINATION_PREFIXES);
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
