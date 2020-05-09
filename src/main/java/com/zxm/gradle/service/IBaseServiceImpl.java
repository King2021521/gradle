package com.zxm.gradle.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/4/29 0029 13:12
 * @Version 1.0
 */
@Service
public class IBaseServiceImpl extends BaseService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void method2() {
        ((BaseService) AopContext.currentProxy()).method3();
    }
}
