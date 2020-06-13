package com.ygn.yby.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应数据code enum
 *
 * @author caods
 * @date 2020/5/19
 * @description: yagena
 */
@Getter
@AllArgsConstructor
public enum RespCodeEnum {

    SUCCESS("200", "成功"),

    PROMPT_MESSAGE("A0000","资源无法操作!"),

    FAIL_HTTP_METHOD_NOT_SUPPORT("405", "请求方式不支持"),

    ERROR("500", "系统异常,请联系管理员"),

    NOT_FOUND("404", "未找到访问路径"),

    PARAMETER_ERROR("A0400", "参数异常"),

    PARAMETER_FORMAT_ERROR("A0421", "参数格式不匹配"),

    USER_RESOURCE("A0600", "用户资源异常"),

    USER_AUTHENTICATION_FAILED("A0220", "用户身份校验失败"),

    USER_LOGIN_EXPIRED("A0230", "用户登陆已过期"),

    USER_ACCOUNT_NOT_EXIST("A0201", "用户账户不存在"),

    ;

    private String code;

    private String message;

}
