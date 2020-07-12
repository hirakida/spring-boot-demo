package com.example.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;

public class HelloAppender<E> extends AppenderBase<E> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloAppender.class);
    private Layout<E> layout;
    private String message;

    public Layout<E> getLayout() {
        return layout;
    }

    public void setLayout(Layout<E> layout) {
        this.layout = layout;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected void append(E eventObject) {
        String event = layout.doLayout(eventObject);
        LOGGER.info("message={}", message);
    }
}
