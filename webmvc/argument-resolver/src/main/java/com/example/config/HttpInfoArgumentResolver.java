package com.example.config;

import java.util.Arrays;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HttpInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return HttpRequestInfo.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        Cookie[] cookies = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getCookies();
        Locale locale = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getLocale();
        String userAgent = nativeWebRequest.getHeader(HttpHeaders.USER_AGENT);
        return new HttpRequestInfo(Arrays.asList(cookies), locale, userAgent);
    }
}
