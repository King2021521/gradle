package com.zxm.gradle.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
public class FilterInterceptor {
    /*@Pointcut("execution(* com.zxm.gradle.config.aop..*(..))")
    public void pointcut() {

    }*/

    @Pointcut("@annotation(com.zxm.gradle.config.aop.Filter)")
    public void pointcut1() {
        System.out.println("解析注解filter----");
    }

    /*@Before("pointcut()")
    public void beforeFunc() {
        System.out.println("beforeFunc ...");

    }

    @After("pointcut()")
    public void afterFunc() {
        System.out.println("afterFunc ...");

    }

    @AfterReturning("pointcut()")
    public void returnFunc() {
        System.out.println("returnFunc ...");

    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void throwExpFunc(Exception e) {
        System.out.println("throwExpFunc ...");
        System.out.println(e.getMessage());

    }*/

    @Around("pointcut1()")
    public Object aroundFunc(ProceedingJoinPoint point) {
        System.out.println("调用开始 ...");
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        System.out.println("代理的方法名：" + methodSignature.getMethod().getName());

        Filter filter = methodSignature.getMethod().getAnnotation(Filter.class);
        if (filter != null) {
            Object[] bb = point.getArgs();
            List<String> ss = (List<String>) bb[0];

            if (ss != null && ss.size() > 0) {
                ss = ss.stream().filter(c -> c.contains("1")).collect(Collectors.toList());
            }

            if(ss.isEmpty()){
                throw new RuntimeException("数组中没用含1的元素");
            }
            try {
                Object result = point.proceed();
                return result;
            } catch (Throwable throwable) {
                System.out.println("around after throws ...");
            }
            /*bb[0] = ss;
            try {
                point.proceed(bb);
            } catch (Throwable e) {
                System.out.println("around after throws ...");
            }*/
        }

        System.out.println("调用结束");
        return null;
    }
}
