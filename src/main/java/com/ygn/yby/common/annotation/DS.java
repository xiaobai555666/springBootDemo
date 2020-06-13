package com.ygn.yby.common.annotation;

import com.ygn.yby.common.enums.DBTypeEnum;

import java.lang.annotation.*;

/**
 * 动态数据源注解
 * @author yby
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface DS {

    DBTypeEnum value() default DBTypeEnum.db1;
}

