package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final SessionScopedBean sessionScopedBean;

    @GetMapping("/")
    public Map<String, Object> index(HttpSession session) {
        log.info("{}", sessionScopedBean);
        if (sessionScopedBean.getSessionId() == null) {
            sessionScopedBean.setSessionId(session.getId());
            sessionScopedBean.setLocalDateTime(LocalDateTime.now());
        }
        return Map.of("sessionId", sessionScopedBean.getSessionId(),
                      "localDateTime", sessionScopedBean.getLocalDateTime());
    }

    @GetMapping("/invalidate")
    public void invalidate(HttpSession session) {
        log.info("invalidate: {}", sessionScopedBean);
        session.invalidate();
        log.info("invalidate: {}", sessionScopedBean);
    }
}
