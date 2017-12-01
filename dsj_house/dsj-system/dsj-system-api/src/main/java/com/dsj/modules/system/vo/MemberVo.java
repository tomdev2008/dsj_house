package com.dsj.modules.system.vo;

import com.dsj.modules.system.po.MemberPo;

public class MemberVo extends MemberPo{

	private String isBlackName;
	private String signupOriginName;
	public String getIsBlackName() {
		return isBlackName;
	}
	public void setIsBlackName(String isBlackName) {
		this.isBlackName = isBlackName;
	}
	public String getSignupOriginName() {
		return signupOriginName;
	}
	public void setSignupOriginName(String signupOriginName) {
		this.signupOriginName = signupOriginName;
	}
}
