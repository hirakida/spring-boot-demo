package com.example.interceptor;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring MVC管理下のスレッド用
 */
@Slf4j
public class MyCallableProcessingInterceptor extends CallableProcessingInterceptorAdapter {

    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("beforeConcurrentHandling");
    }

    @Override
    public <T> void preProcess(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("preProcess");
    }

    @Override
    public <T> void postProcess(NativeWebRequest request, Callable<T> task, Object concurrentResult)
            throws Exception {
        log.info("postProcess");
    }

    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        log.error("handleTimeout");
        throw new IllegalStateException('[' + task.getClass().getName() + "] timed out");
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("afterCompletion");
    }
}
