package com.example;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final AppSession appSession;

    @GetMapping("/")
    public Response index() {
        log.info("{}", appSession);
        if (appSession.getUuid() == null) {
            appSession.setUuid(UUID.randomUUID().toString());
            appSession.setLocalDateTime(LocalDateTime.now());
        }
        Response response = new Response();
        BeanUtils.copyProperties(appSession, response);
        return response;
    }

    @GetMapping("/clear")
    public void clear(HttpSession session) {
        session.invalidate();
        log.info("{}", appSession);
    }

    @Data
    public static class Response {
        private String uuid;
        private LocalDateTime localDateTime;
    }
}
