package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SessionApiController {
    private final SessionRepository<? extends Session> sessionRepository;
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
        log.info("{}", sessionScopedBean);
        session.invalidate();
        log.info("{}", sessionScopedBean);
    }

    @PostMapping("/sessions")
    public Session create() {
        return sessionRepository.createSession();
    }

    @GetMapping("/sessions/{id}")
    public Session findById(@PathVariable String id) {
        return sessionRepository.findById(id);
    }

    @DeleteMapping("/sessions/{id}")
    public void deleteById(@PathVariable String id) {
        sessionRepository.deleteById(id);
    }
}
