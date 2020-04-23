package com.zxm.gradle.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName InitBean
 * @Author zxm
 * @Description
 * @Date 2019/12/16 14:46
 */
public class InitBean {
    private static final Logger logger = LogManager.getLogger(InitBean.class);
    public InitBean() {
        logger.info("初始化-----------initBean");
    }

    public void init(){
        logger.info("init调用-----------------");
    }
}
