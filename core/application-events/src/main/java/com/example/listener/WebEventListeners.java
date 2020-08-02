package com.example.listener;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebEventListeners {
    @EventListener
    public void handleEvent(WebServerInitializedEvent event) {
        log.info("WebServerInitializedEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ServletWebServerInitializedEvent event) {
        log.info("ServletWebServerInitializedEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(RequestHandledEvent event) {
        log.info("RequestHandledEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ServletRequestHandledEvent event) {
        log.info("ServletRequestHandledEvent={}", event.getSource());
    }
}
