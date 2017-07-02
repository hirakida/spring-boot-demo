package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.DateTime;
import com.example.model.IpAddr;
import com.example.client.JsonTestApiClient;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ApiController {

    final JsonTestApiClient jsonTestApiClient;

    @GetMapping("/ip")
    public IpAddr ip() {
        return jsonTestApiClient.getIp();
    }

    @GetMapping("/date")
    public DateTime date() {
        return jsonTestApiClient.getDateTime();
    }
}
