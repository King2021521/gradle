package com.zxm.gradle.config.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendMsg {
    public void ss(List<String> mobiles){
        Integer size = ((SendMsg)AopContext.currentProxy()).send(mobiles);
        System.out.println("size is----:"+size);
    }

    @Filter
    public Integer send(List<String> mobiles){
        System.out.println("-----"+mobiles);
        return mobiles.size();
    }
}
