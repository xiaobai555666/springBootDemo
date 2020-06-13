package com.ygn.yby.common.exception;

import com.ygn.yby.common.enums.RespCodeEnum;
import com.ygn.yby.common.enums.ResponseCode;

/**
 *
 * description: 自定义异常
 *
 * @auther: yby
 * @date: 2019/1/12 15:11
 */
public class BusinessRuntimeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8592625429988913737L;
	
	private RespCodeEnum errorCode;
	
	public BusinessRuntimeException() {
		
	}
	
	public BusinessRuntimeException(String message) {
		super(message);
	}

//	public BusinessRuntimeException(String errorCode, String message) {
//		super(message);
//		this.errorCode = errorCode;
//
//	}
	
	public BusinessRuntimeException(RespCodeEnum responseErrorCode) {
		super(responseErrorCode.getCode());
		this.errorCode = responseErrorCode;
	}
	
	public RespCodeEnum getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(RespCodeEnum errorCode) {
		this.errorCode = errorCode;
	}
	
}
