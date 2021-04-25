package com.example.producer;

import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ListenableFutureCallbackImpl implements ListenableFutureCallback<SendResult<String, User>> {
    @Override
    public void onSuccess(@Nullable SendResult<String, User> result) {
        if (result != null) {
            log.info("record={}", result.getProducerRecord());
        }
    }

    @Override
    public void onFailure(Throwable e) {
        log.error(e.getMessage(), e);
    }
}
