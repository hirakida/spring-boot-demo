package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
@SuppressWarnings("serial")
public class SessionBean implements Serializable {
    private String uuid;
    private LocalDateTime localDateTime;
}
