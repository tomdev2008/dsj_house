package com.dsj.modules.im.vo;

import com.dsj.modules.im.po.IMDirectoryPo;

public class IMDirectoryVo extends IMDirectoryPo {

	private Integer agentCode;		// 经纪人ID
	private String agentName;		// 姓名
	private String tellPhone;		// 电话号码
	private String company;		    // 经纪公司
	
	public Integer getAgentCode() {
		return agentCode;
	}
	
	public void setAgentCode(Integer agentCode) {
		this.agentCode = agentCode;
	}
	
	public String getAgentName() {
		return agentName;
	}
	
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public String getTellPhone() {
		return tellPhone;
	}
	
	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
    
}
