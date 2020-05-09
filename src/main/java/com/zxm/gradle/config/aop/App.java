package com.zxm.gradle.config.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(FilterInterceptor.class,AopConfig.class,SendMsg.class);
        applicationContext.getBean(SendMsg.class).ss(Arrays.asList("111","222"));
    }
}
