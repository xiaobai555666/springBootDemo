package com.ygn.yby.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 多数据源枚举
 */
@Getter
@AllArgsConstructor
public enum DBTypeEnum {

    db1("db1"), db2("db2");

    private String value;

}