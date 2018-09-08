package com.example;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final SessionBean sessionBean;

    @GetMapping("/")
    public Response index() {
        log.info("{}", sessionBean);
        if (sessionBean.getUuid() == null) {
            sessionBean.setUuid(UUID.randomUUID().toString());
            sessionBean.setLocalDateTime(LocalDateTime.now());
        }
        return new Response(sessionBean.getUuid(), sessionBean.getLocalDateTime());
    }

    @GetMapping("/clear")
    public void clear(HttpSession session) {
        log.info("sessionId={}", session.getId());
        session.invalidate();
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private String uuid;
        private LocalDateTime localDateTime;
    }
}
