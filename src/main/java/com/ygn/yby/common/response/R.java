package com.ygn.yby.common.response;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回数据
 * 
 * @author yby
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 200;
	
	public R() {
		put("status", 200);
		put("message", "操作成功");
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String message) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, message);
	}
	
	public static R error(int status, String message) {
		R r = new R();
		r.put("status", status);
		r.put("message", message);
		return r;
	}

	public static R ok(String message) {
		R r = new R();
		r.put("message", message);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Integer getStatus (){
		return (Integer)this.get("status");
	}

	public String getMessage (){
		return (String)this.get("Message");
	}
}
