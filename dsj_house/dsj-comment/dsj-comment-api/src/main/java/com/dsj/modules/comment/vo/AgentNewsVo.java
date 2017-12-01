package com.dsj.modules.comment.vo;

import com.dsj.modules.comment.po.AgentNewsPo;

public class AgentNewsVo extends AgentNewsPo{
	
	private String name;//经纪人姓名
	private String company;//经纪人公司ID
	private Integer agentCode;//经纪人编号
	private String tellPhone;//手机号
	
	private String avatarUrl; //小头像
	private String mainCommunity; //主营小区
	private String companyName; //公司名称
	private String shortName; //公司名称缩写
	
	private String username ;
	private String realname ;
	private String mobile400 ;//经纪人400电话
	
	private Integer clicktype;//是否点赞  null没点赞  1 顶  2踩
	
	public String getMainCommunity() {
		return mainCommunity;
	}
	public void setMainCommunity(String mainCommunity) {
		this.mainCommunity = mainCommunity;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(Integer agentCode) {
		this.agentCode = agentCode;
	}
	public String getTellPhone() {
		return tellPhone;
	}
	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getMobile400() {
		return mobile400;
	}
	public void setMobile400(String mobile400) {
		this.mobile400 = mobile400;
	}
	public Integer getClicktype() {
		return clicktype;
	}
	public void setClicktype(Integer clicktype) {
		this.clicktype = clicktype;
	}
}
