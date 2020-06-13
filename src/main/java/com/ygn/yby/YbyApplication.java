package com.ygn.yby;

import com.ygn.yby.common.canal.annotation.EnableCanalClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ygn.yby.*"})
@MapperScan(basePackages = {"com.ygn.yby.mapper"})
//指定aop事务执行顺序，已保证在切换数据源的后面
@EnableTransactionManagement(order = 2)
@EnableCanalClient
public class YbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(YbyApplication.class, args);
    }

}
