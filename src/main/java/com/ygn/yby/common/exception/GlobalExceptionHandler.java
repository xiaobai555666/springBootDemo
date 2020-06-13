package com.ygn.yby.common.exception;

import com.ygn.yby.common.enums.RespCodeEnum;
import com.ygn.yby.dto.common.RespDTO;
import io.lettuce.core.RedisConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局异常处理类
 *
 * @author caods
 * @since 2018/11/19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public RespDTO ExceptionHandle(Exception e) {

        log.error("系统发生异常:{}", e);

        return RespDTO.error(RespCodeEnum.ERROR, e.getMessage());
    }

    /**
     * 单个形参校验类上加@Validated
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RespDTO ConstraintViolationExceptionHandle(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();

        e.getConstraintViolations().forEach(constraintViolation -> sb.append(constraintViolation.getMessage()));
        String msg = sb.toString();

        log.error("参数:{},绑定异常:{}", msg, e);

        return RespDTO.error(RespCodeEnum.PARAMETER_FORMAT_ERROR, e.getMessage());
    }

    /**
     * 实体类校验  参数前面加@Validated
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public RespDTO BindExceptionHandle(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder sb = new StringBuilder();

        fieldErrors.forEach(fieldError -> sb.append(fieldError.getField() + ":" + fieldError.getDefaultMessage() + ";"));
        String msg = sb.toString().substring(0, sb.lastIndexOf(";"));

        log.error("参数:{},绑定异常:{}", msg, e);
        return RespDTO.error(RespCodeEnum.PARAMETER_FORMAT_ERROR, e.getMessage());
    }

    /**
     * redis 连接异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RedisConnectionException.class)
    public RespDTO RedisConnectionFailureExceptionHandle(RedisConnectionException e) {

        log.error("redis连接出现异常:{}", e);

        return RespDTO.error(RespCodeEnum.ERROR, e.getMessage());
    }
}
