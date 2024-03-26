package com.example;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Component
@SessionScope
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@Data
public class SessionScopedBean {
    private String id;
    private LocalDateTime dateTime;
}
