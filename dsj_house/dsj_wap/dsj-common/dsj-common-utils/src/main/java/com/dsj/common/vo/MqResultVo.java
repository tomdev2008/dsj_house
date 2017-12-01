package com.dsj.common.vo;

import com.dsj.common.enums.StatusCode;

public class MqResultVo {
	private Integer code;
	
	private String msg;
	
	private Object data;
	
	public void setStatusCode(StatusCode statusCode){
		if(statusCode==null)
			return;
		setCode(statusCode.getCode());
		setMsg(statusCode.getMsg());
	}

	

	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
