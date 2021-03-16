package com.example.web.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatrixController {
    /**
     * http://localhost:8080/matrix/key1=value1;key2=value2...
     */
    @GetMapping("/matrix/{values}")
    public Map<String, String> matrix(@MatrixVariable Map<String, String> values) {
        return values;
    }
}
