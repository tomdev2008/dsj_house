package com.dsj.common.vo;

import java.io.Serializable;

import com.dsj.common.enums.StatusCode;

public class AjaxResultVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private String message;
	
	private Object data;
	
	public void setStatusCode(StatusCode statusCode){
		if(statusCode==null)
			return;
		setStatus(statusCode.getCode());
		setMessage(statusCode.getMsg());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
