package com.dsj.modules.comment.vo;

import com.dsj.modules.comment.po.FeedbackPo;

public class FeedbackVo extends FeedbackPo {
	
	private String userName;
	private String tellPhone;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTellPhone() {
		return tellPhone;
	}
	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	
}
