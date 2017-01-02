package com.example;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SampleService {

    /**
     * value
     * - retry処理対象のExceptionを定義する
     * maxAttempts
     * - 最大実行回数（リトライ回数ではなくて、初回の実行を含めた回数）
     * - default値は3
     * backoff
     * - delayなどが設定できる
     */
    @Retryable(value = { IllegalArgumentException.class, IllegalStateException.class },
               maxAttempts = 4,
               backoff = @Backoff(delay = 500))
    public String retry(long id) {
        log.info("service start id={}", id);

        if (id == 0) {
            // @Recoverを定義しているため、retry out後にrecoverメソッドで戻り値を返す
            throw new IllegalArgumentException();
        }
        if (id == 10) {
            // @Recoverを定義していないため、retry out後にExhaustedRetryExceptionが発生する
            throw new IllegalStateException();
        }
        if (id == 100) {
            // valueにExceptionを指定していないため、retryなしでExhaustedRetryExceptionが発生する
            throw new RuntimeException();
        }
        log.info("service end id={}", id);
        return "ok";
    }

    /**
     * retry outした場合のrecover処理
     * このメソッドの戻り値が@Retryableメソッドの戻り値になる
     */
    @Recover
    public String recover(IllegalArgumentException e, long id) {
        log.info("recover id={}", id);
        return "retry out";
    }
}
