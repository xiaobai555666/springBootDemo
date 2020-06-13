package com.ygn.yby.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ygn.yby.common.enums.DBTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: yby
 * @description: 数据源配置类
 * @author: yby
 * @create: 2020-06-09 17:41
 **/
@Configuration
public class DataSourceConfig {
    /**
     *  数据源1
     */
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource Ds1(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源2
     */
    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.litemall")
    public DataSource Ds2(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源切换: 通过AOP在不同数据源之间动态切换
     */
    @Primary
    @Bean
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DBTypeEnum.db1.getValue(), Ds1());
        targetDataSources.put(DBTypeEnum.db2.getValue(), Ds2());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(Ds1());
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事务
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }




    //主数据源配置 ds1数据源
/*    @Primary
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSourceProperties ds1DataSourceProperties() {
        return new DataSourceProperties();
    }

    //主数据源 ds1数据源
    @Primary
    @Bean(name = "db1")
    public DataSource ds1DataSource(@Qualifier("db1") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    //第二个ds2数据源配置
    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.litemall")
    public DataSourceProperties ds2DataSourceProperties() {
        return new DataSourceProperties();
    }

    //第二个ds2数据源
    @Bean("db2")
    public DataSource ds2DataSource(@Qualifier("db2") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }*/

}
