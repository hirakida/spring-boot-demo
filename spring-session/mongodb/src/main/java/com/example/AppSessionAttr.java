package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = WebApplicationContext.SCOPE_SESSION)
@Data
@SuppressWarnings("serial")
public class AppSessionAttr implements Serializable {
    private String uuid;
    private LocalDateTime localDateTime;
}
