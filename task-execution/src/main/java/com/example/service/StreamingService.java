package com.example.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StreamingService {

    private static final int DATA_COUNT = 5;
    private static final long TIMEOUT = 1;

    public StreamingResponseBody streaming() {
        return outputStream -> {
            log.info("Service start");
            IntStream.rangeClosed(1, DATA_COUNT)
                     .forEach(i -> {
                         try {
                             TimeUnit.SECONDS.sleep(TIMEOUT);
                             String data = "data" + i;
                             outputStream.write((data + "\r\n").getBytes());
                             outputStream.flush();
                             log.info("send: {}", data);
                         } catch (InterruptedException | IOException e) {
                             log.error(e.toString());
                         }
                     });
            log.info("Service end");
        };
    }
}
