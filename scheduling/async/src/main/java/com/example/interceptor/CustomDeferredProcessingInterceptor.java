package com.example.interceptor;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomDeferredProcessingInterceptor extends DeferredResultProcessingInterceptorAdapter {

    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, DeferredResult<T> deferredResult)
            throws Exception {
        log.info("beforeConcurrentHandling");
    }

    @Override
    public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        log.info("preProcess");
    }

    @Override
    public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult,
                                Object concurrentResult) throws Exception {
        log.info("postProcess");
    }

    @Override
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult)
            throws Exception {
        log.info("handleTimeout");
        return true;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult)
            throws Exception {
        log.info("afterCompletion");
    }
}
