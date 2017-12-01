package com.dsj.common.vo;

import java.io.Serializable;

import com.dsj.common.enums.ApiStatusCode;

/**
 * api 返回结果通用结果集
 * @author jiangzhengliang
 */
public class CommResultVo implements Serializable {
	
	private static final long serialVersionUID = 3517344801693747496L ;

	private Integer returnCode;
	
	private String returnMsg;
	
	private Object data;
	
	
	public void setStatusCode(ApiStatusCode apiStatusCode){
		if(apiStatusCode==null)
			return;
		setReturnCode(apiStatusCode.getCode());
		setReturnMsg(apiStatusCode.getMsg());
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}