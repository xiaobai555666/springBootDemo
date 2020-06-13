package com.ygn.yby.dto.common;

import cn.hutool.core.util.StrUtil;
import com.ygn.yby.common.enums.ResponseCode;
import net.sf.json.JSONObject;

/**
 *
 * description: 统一结果响应结果
 *
 * @auther: YBY
 * @date: 2019/2/14 14:27
 */
public class ResultDTO<T> {

	private String message;
	private String resultCode;
	private T data;

	private ResultDTO(T data) {
		this.resultCode = ResponseCode.SUCCESS.getCode();
		this.message = ResponseCode.SUCCESS.getMessage();
		this.data = data;
	}

	private ResultDTO(ResponseCode responseCode) {
		if(responseCode == null){
			return;
		}
		this.resultCode = responseCode.getCode();
		this.message = responseCode.getMessage();
	}

	/**
	 * 成功，返回对象
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResultDTO<T> successObject(T data){
		return new ResultDTO<T>(data);
	}

	/**
	 * 成功，无数据返回对象
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ResultDTO<T> successObject(){
		return (ResultDTO<T>) successObject("");
	}

	/**
	 * 失败
	 * @param responseCode
	 * @param <T>
	 * @return
	 */
	public static <T> ResultDTO<T> errorObject(ResponseCode responseCode){
		return new ResultDTO<>(responseCode);
	}

	/**
	 * 失败，带自定义消息数据
	 * @param responseCode
	 * @param message 自定义消息
	 * @param <T>
	 * @return
	 */
	public static <T> ResultDTO<T> errorObject(ResponseCode responseCode, String message){
		String codeMessage = responseCode.getMessage();
		if (StrUtil.isNotBlank(message)) {
			responseCode.setMessage(codeMessage.concat("->").concat(message));
		}
		ResultDTO resultDto = new ResultDTO<>(responseCode);
		responseCode.setMessage(codeMessage);
		return resultDto;
	}

	/** 以下方法为返回对象信息定义 **/

	/**
	 * 成功，返回对象信息
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> String success(T data){
		return JSONObject.fromObject(successObject(data)).toString();
	}

	/**
	 * 成功，无数据返回对象信息
	 * @param <T>
	 * @return
	 */
	public static <T> String success(){
		return success("");
	}

	/**
	 * 失败
	 * @param <T>
	 * @return
	 */
	public static <T> String fail(){
		return JSONObject.fromObject(errorObject(ResponseCode.FAIL)).toString();
	}

	/**
	 * 失败
	 * @param responseCode
	 * @param <T>
	 * @return
	 */
	public static <T> String error(ResponseCode responseCode){
		return JSONObject.fromObject(errorObject(responseCode)).toString();
	}

	/**
	 * 失败，带自定义消息数据
	 * @param responseCode
	 * @param message 自定义消息
	 * @param <T>
	 * @return
	 */
	public static <T>String error(ResponseCode responseCode, String message){
		return JSONObject.fromObject(errorObject(responseCode, message)).toString();
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public String getResultCode() {
		return resultCode;
	}

}
