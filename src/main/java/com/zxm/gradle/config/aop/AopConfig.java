package com.zxm.gradle.config.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackageClasses= {FilterInterceptor.class})
@EnableAspectJAutoProxy(exposeProxy = true)
public class AopConfig {
}
