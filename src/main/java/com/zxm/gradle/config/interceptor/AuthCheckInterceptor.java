package com.zxm.gradle.config.interceptor;

import com.zxm.gradle.config.annotation.LoginIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCheckInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).getMethod().getAnnotation(LoginIgnore.class) != null) {
                return true;
            }
        }

        String token = httpServletRequest.getHeader("Authorization");
        if(!"JAVA121019".equals(token)){
            throw new RuntimeException("身份不合法");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
