package com.ygn.yby.common.aop;

import com.ygn.yby.common.annotation.DS;
import com.ygn.yby.common.enums.DBTypeEnum;
import com.ygn.yby.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program: yby
 * @description: 多数据源配置, 拦截器配置
 * @author: yby
 * @create: 2020-06-09 18:09
 **/
@Aspect
@Component
@Slf4j
@Order(1)
public class DataSourceAspect {

    private final String DEFAULT_DATA_SOURCE = "db1";

    @Pointcut("execution(public * com.ygn.yby.service.*.*(..))")
    public void dataSourcePoint() {}

    @Before("dataSourcePoint()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class clazz = signature.getDeclaringType();
        Method method = signature.getMethod();
        DBTypeEnum dbTypeEnum = DBTypeEnum.db1;
        try {
            // 类上有注解 方法上没有 使用类注解数据源
            if (clazz.isAnnotationPresent(DS.class) && !method.isAnnotationPresent(DS.class)) {
                DS annotation = (DS) clazz.getAnnotation(DS.class);
                dbTypeEnum = annotation.value();
            }
            // 类上有注解 方法上有注解 使用方法注解数据源
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                dbTypeEnum = annotation.value();
            }
            // 此处添加线程对应的数据源到上下文
            // 在AbstractRoutingDataSource子类中拿到数据源, 加载后进行配置
            DataSourceContextHolder.setDataSource(dbTypeEnum.getValue());
            log.info("generate data source : " + dbTypeEnum.getValue());
        } catch (Exception e) {
            log.info("error", e);
        }

    }

    /**
     * 清除数据源, 方法执行完成后, 清除数据源
     */
    @After("dataSourcePoint()")
    public void after(JoinPoint joinPoint) {
        DataSourceContextHolder.clear();
    }
}
