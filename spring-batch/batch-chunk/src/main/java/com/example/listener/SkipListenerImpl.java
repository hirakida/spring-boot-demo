package com.example.listener;

import org.springframework.batch.core.SkipListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SkipListenerImpl<T, S> implements SkipListener<T, S> {
    @Override
    public void onSkipInRead(Throwable t) {
        log.error("OnSkipInRead", t);
    }

    @Override
    public void onSkipInProcess(T item, Throwable t) {
        log.error("OnSkipInProcess {}", item, t);
    }

    @Override
    public void onSkipInWrite(S item, Throwable t) {
        log.error("OnSkipInWrite {}", item, t);
    }
}
