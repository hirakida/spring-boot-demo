package com.example.support;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HttpRequestInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return HttpRequestInfo.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        List<Cookie> cookies = Optional.ofNullable(nativeWebRequest.getNativeRequest(HttpServletRequest.class))
                                       .map(HttpServletRequest::getCookies)
                                       .map(List::of)
                                       .orElse(List.of());
        Locale locale = Optional.ofNullable(nativeWebRequest.getNativeRequest(HttpServletRequest.class))
                                .map(HttpServletRequest::getLocale)
                                .orElse(Locale.getDefault());
        String userAgent = nativeWebRequest.getHeader(HttpHeaders.USER_AGENT);
        return new HttpRequestInfo(cookies, locale, userAgent);
    }
}
