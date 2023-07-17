package com.example;

import java.util.List;

import jakarta.servlet.http.Cookie;

public record RequestInfo(List<Cookie> cookies) {}
