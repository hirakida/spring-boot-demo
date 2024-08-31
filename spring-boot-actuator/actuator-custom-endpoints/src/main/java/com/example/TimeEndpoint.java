package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@WebEndpoint(id = "time")
public class TimeEndpoint {
    @ReadOperation
    public ResponseEntity<Map<String, LocalDateTime>> time() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(Map.of("time", LocalDateTime.now()));
    }
}
