package com.example.streaming;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StreamingService {

    @Async
    public void responseBodyEmitter(ResponseBodyEmitter emitter) {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     sleep(1);

                     String data = "data" + i;
                     try {
                         emitter.send(data + "\r\n");
                     } catch (IOException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                     log.info("send: {}", data);
                 });
        emitter.complete();
    }

    public StreamingResponseBody streamingResponseBody() {
        return outputStream -> IntStream.rangeClosed(6, 10)
                                        .forEach(i -> {
                                            sleep(1);

                                            String data = "data" + i;
                                            try {
                                                outputStream.write((data + "\r\n").getBytes());
                                                outputStream.flush();
                                            } catch (IOException e) {
                                                log.error("{}", e.getMessage(), e);
                                            }
                                            log.info("send: {}", data);
                                        });
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
    }
}
