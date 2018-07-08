package com.hirakida.spring.boot;

import java.time.LocalDateTime;

import org.springframework.boot.actuate.endpoint.mvc.AbstractNamedMvcEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HelloNamedMvcEndpoint extends AbstractNamedMvcEndpoint {

    public HelloNamedMvcEndpoint() {
        super("hello", "/hello", true);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HelloResponse> hello() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new HelloResponse("hello!", LocalDateTime.now()));
    }
}
