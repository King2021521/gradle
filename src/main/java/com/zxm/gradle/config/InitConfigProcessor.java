package com.zxm.gradle.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName InitConfigProcessor
 * @Author zxm
 * @Description
 * @Date 2019/12/16 14:26
 */
public class InitConfigProcessor implements BeanPostProcessor {
    private static final Logger logger = LogManager.getLogger(InitConfigProcessor.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof InitBean) {
            logger.info("Bean '" + beanName + "' created : " + bean.toString() + "---------------");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof InitBean) {
            logger.info("Bean '" + beanName + "' created : " + bean.toString());
        }
        return bean;
    }
}
