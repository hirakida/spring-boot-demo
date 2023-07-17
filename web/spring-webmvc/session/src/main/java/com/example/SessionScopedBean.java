package com.example;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class SessionScopedBean {
    private LocalDateTime datetime;

    public SessionScopedBean() {
        datetime = LocalDateTime.now();
    }
}
