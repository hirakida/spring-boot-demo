package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandle handler={}", handler);
        // falseを返すとHandlerメソッドが呼ばれない
        return true;
    }

    /**
     * ControllerのHandlerメソッドが正常に終了した後に呼ばれる
     * レスポンスに共通のヘッダーを挿入したり、modelAndViewに共通のデータを挿入できる
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle handler={}", handler);
    }

    /**
     * Viewのレンダリングが終了した後に呼ばれる
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("afterCompletion handler={}", handler);
    }

    // 非同期リクエストのハンドリングの開始後に呼ばれる
    // HandlerInterceptorをimplementsした場合は、このメソッドはない
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request,
                                               HttpServletResponse response,
                                               Object handler) throws Exception {
        log.info("afterConcurrentHandlingStarted handler={}", handler);
    }
}
