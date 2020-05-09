package com.zxm.gradle.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/4/29 0029 13:10
 * @Version 1.0
 */
@Service
public abstract class BaseService {

    public void method1(){
        ((BaseService)AopContext.currentProxy()).method2();
    }

    public abstract void method2();

    @Transactional(rollbackFor = Exception.class)
    public void method3(){
        System.out.println("------");
    }
}
