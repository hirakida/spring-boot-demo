package com.example;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

public class SampleBean {

    /**
     * prototype scope
     * 利用する度にインスタンス化される
     * <p>
     * prototypeでもScoped Proxyは利用可能だが、同じfieldの異なるメソッド呼び出しに対しても、
     * 実際には毎回異なるインスタンスに対してメソッドが呼び出される
     * <p>
     * prototype scopeに対しては@Lookupを使ったほうがいい
     */
    @Component
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Data
    public static class PrototypeBean {
        private long id;
        private String name;
        private long count;

        public long getCount() {
            return ++count;
        }
    }

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
    public static class RequestBean {
        private long id;
        private String name;
        private long count;

        public long getCount() {
            return ++count;
        }
    }

    /**
     * session scope
     * Servlet APIのsession scopeの間だけインスタンスが生存する
     */
    @Component
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Data
    public static class SessionBean {
        private long id;
        private String name;
        private long count;

        public long getCount() {
            return ++count;
        }
    }

    /**
     * Servlet APIのapplication scopeの間だけインスタンスが生存する
     */
    @Component
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Data
    public static class ApplicationBean {
        private long id;
        private String name;
        private long count;

        public long getCount() {
            return ++count;
        }
    }

    /**
     * singleton scope
     * デフォルトでsingletonになるため、以下の@Scopeは無くても変わらない
     */
    @Component
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Data
    public static class SingletonBean {
        private long id;
        private String name;
        private long count;

        public long getCount() {
            return ++count;
        }
    }
}
