package com.example.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

/**
 * session scope
 * Servlet APIのsession scopeの間だけインスタンスが生存する
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class SessionBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
