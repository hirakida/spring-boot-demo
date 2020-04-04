package com.example.model;

import java.util.List;

import javax.servlet.http.Cookie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestInfo {
    private List<Cookie> cookies;
}
