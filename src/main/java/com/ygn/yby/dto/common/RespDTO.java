package com.ygn.yby.dto.common;

import cn.hutool.core.util.StrUtil;
import com.ygn.yby.common.enums.RespCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应处理
 *
 * @author yby
 * @date 2020/5/19
 * @description: yagena
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespDTO<T> {

    private String msg;

    private String code;

    private T data;

    /**
     * 请求成功 resp数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespDTO success(T data) {
        return RespDTO.builder()
                .code(RespCodeEnum.SUCCESS.getCode())
                .msg(RespCodeEnum.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    /**
     * 请求成功 无数据
     *
     * @return
     */
    public static RespDTO success() {
        return RespDTO.builder()
                .code(RespCodeEnum.SUCCESS.getCode())
                .msg(RespCodeEnum.SUCCESS.getMessage())
                .data(StrUtil.EMPTY)
                .build();
    }

    /**
     * 请求失败
     *
     * @param respCodeEnum
     * @return
     */
    public static RespDTO error(RespCodeEnum respCodeEnum) {
        return RespDTO.builder()
                .code(respCodeEnum.getCode())
                .msg(respCodeEnum.getMessage())
                .data(StrUtil.EMPTY)
                .build();
    }

    /**
     * 请求失败 个性msg
     *
     * @param respCodeEnum
     * @param message
     * @return
     */
    public static RespDTO error(RespCodeEnum respCodeEnum, String message) {
        String data = StrUtil.isEmpty(message) ? StrUtil.EMPTY : message;
        return RespDTO.builder()
                .code(respCodeEnum.getCode())
                .msg(respCodeEnum.getMessage())
                .data(data)
                .build();
    }
}
