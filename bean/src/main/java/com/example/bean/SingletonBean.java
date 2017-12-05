package com.example.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * singleton scope
 * デフォルトでsingletonになるため、以下の@Scopeは無くても変わらない
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Data
public class SingletonBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}

