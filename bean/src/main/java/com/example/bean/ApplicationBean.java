package com.example.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

/**
 * Servlet APIのapplication scopeの間だけインスタンスが生存する
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class ApplicationBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
