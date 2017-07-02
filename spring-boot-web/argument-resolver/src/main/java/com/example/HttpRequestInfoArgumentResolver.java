package com.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HttpRequestInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // trueを返却すると、resolveArgumentが呼ばれる
        return HttpRequestInfo.class.isAssignableFrom(parameter.getParameterType());
    }

    /**
     * Handlerメソッドの引数に渡すobjectを生成する
     */
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpSession session = nativeWebRequest.getNativeRequest(HttpServletRequest.class)
                                              .getSession(false);
        Cookie[] cookies = nativeWebRequest.getNativeRequest(HttpServletRequest.class)
                                           .getCookies();
        String userAgent = nativeWebRequest.getHeader(HttpHeaders.USER_AGENT);
        return HttpRequestInfo.builder()
                              .session(session)
                              .cookies(cookies)
                              .userAgent(userAgent)
                              .build();
    }
}
