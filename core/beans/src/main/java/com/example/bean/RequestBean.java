package com.example.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

/**
 * request scope
 * Servlet APIのrequest scopeの間だけインスタンスが生存する
 * <p>
 * proxyModeを有効にしないと、ControllerなどsingletonスコープのBeanにインジェクションすると同じインスタンスが使われる
 * Scoped Proxyは、Proxyで包んだ状態でBeanをinjectionし、injectionされたBeanのメソッドを呼ぶと、
 * 実際はDIコンテナからlookupしたBeanにメソッドの実行を委譲する
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class RequestBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
