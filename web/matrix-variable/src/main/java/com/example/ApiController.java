package com.example;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    /**
     * http://localhost:8080/key1=value1;key2=value2...
     */
    @GetMapping("/{matrix}")
    public Map<String, String> index(@MatrixVariable Map<String, String> matrix) {
        return matrix;
    }
}
