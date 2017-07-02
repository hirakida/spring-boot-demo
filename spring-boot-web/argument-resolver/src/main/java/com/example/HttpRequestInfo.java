package com.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HttpRequestInfo {
    private HttpSession session;
    private Cookie[] cookies;
    private String userAgent;
}
