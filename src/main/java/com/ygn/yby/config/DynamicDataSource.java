package com.ygn.yby.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @program: yby
 * @description: 动态数据源配置
 * @author: yby
 * @create: 2020-06-09 10:58
 **/
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource{

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("当前数据源:{}", DataSourceContextHolder.getDataSource());
        return DataSourceContextHolder.getDataSource();
    }
}
