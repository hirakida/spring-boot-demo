package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

import io.rsocket.transport.ClientTransport;
import io.rsocket.transport.netty.client.WebsocketClientTransport;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
public class RSocketConfig {

    @Bean
    public ClientTransport clientTransport() {
        return WebsocketClientTransport.create(HttpClient.from(TcpClient.create()
                                                                        .host("localhost")
                                                                        .port(7000)),
                                               "/rsocket");
    }

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.connect(clientTransport()).block();
    }
}
