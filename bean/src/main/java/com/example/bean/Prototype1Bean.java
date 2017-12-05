package com.example.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

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
public class Prototype1Bean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
