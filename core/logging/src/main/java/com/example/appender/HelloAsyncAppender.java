package com.example.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class HelloAsyncAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloAsyncAppender.class);

    @Override
    protected void append(ILoggingEvent eventObject) {
        LOGGER.info("{}", eventObject);
    }
}
