package com.recipelibrary.api.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
public class LogExecutionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                request.setAttribute("RLRequestStartTime", System.currentTimeMillis());
                log.info("Starting controller method for {}.{}",
                        handlerMethod.getBeanType().getSimpleName(),
                        handlerMethod.getMethod().getName());
            }
        } catch (Exception e) {
            log.error("Caught an exception while executing handler method", e);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                log.info("Completed controller method for {}.{} takes {} ms",
                        handlerMethod.getBeanType().getSimpleName(),
                        handlerMethod.getMethod().getName(),
                        (System.currentTimeMillis() - (Long) request.getAttribute("RLRequestStartTime")));
            }
        } catch (Exception e) {
            log.error("Caught an exception while executing handler method", e);
        }
    }
}