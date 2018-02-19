package com.example.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Component
@ApplicationScope
@Data
public class ApplicationBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
