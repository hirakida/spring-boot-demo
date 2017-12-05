package com.example.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RequestFilter1 extends OncePerRequestFilter {

    /**
     * アプリケーション起動時に呼び出される
     */
    @Override
    protected void initFilterBean() throws ServletException {
        log.info("initFilterBean");
    }

    /**
     * ServletRequestListenerの後でInterceptorの前に呼び出される
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal");
        // 後続処理(次のFilter又はServlet)を呼び出す
        // 後続処理を呼び出したくない場合は、このタイミングでreturnする
        filterChain.doFilter(request, response);
    }

    /**
     * アプリケーション終了時に呼び出される
     */
    @Override
    public void destroy() {
        log.info("destroy");
    }
}
