package com.example.support;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpRequestInfo {
    private List<Cookie> cookies;
    private Locale locale;
    private String userAgent;
}
