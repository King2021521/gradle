package com.zxm.gradle.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zxm.gradle.dao.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 * @MapperScan 注解指示mapper接口的位置, 注意使用 markerInterface属性将基础映射类过滤掉不参与Spring扫描
 * @author zxm
 * @date 2019-09-10
 */
@Configuration
@MapperScan(basePackages = "com.zxm.gradle.dao", markerInterface = BaseMapper.class)
public class MybatisPlusConfig {

    /**
     * 要想在查询中使用分页Page功能, 那么必须配置 PaginationInterceptor, 将其注入到Spring容器中
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor.setDialectType("mysql");
    }
}

