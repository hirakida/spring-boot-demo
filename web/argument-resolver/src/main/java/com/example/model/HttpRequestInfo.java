package com.example.model;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HttpRequestInfo {
    private List<Cookie> cookies;
    private Locale locale;
    private String userAgent;
}
