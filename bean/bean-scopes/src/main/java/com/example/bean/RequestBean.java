package com.example.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Component
@RequestScope
@Data
public class RequestBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
