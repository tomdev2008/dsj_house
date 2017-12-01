package com.dsj.modules.newhouse.vo;

import java.io.Serializable;

public class NewHouseAuthMsgVo  implements Serializable {
	private String shMsg;
	private Integer status;
	public String getShMsg() {
		return shMsg;
	}
	public void setShMsg(String shMsg) {
		this.shMsg = shMsg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
