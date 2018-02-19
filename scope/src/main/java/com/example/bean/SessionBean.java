package com.example.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class SessionBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
