package com.dsj.modules.system.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String realname;       //真实姓名
	private String username;		//用户名
	private String imPassword;		//环信密码
	private Long id;
	private String url;
	private String agentProductName;
	private String phone;  //经纪人400电话
	private String ext;   //400电话分机号
	private String developerProductName;
	private Integer status;
	private String avatar;//头像
	private String auditName;//经纪人审核状态(已认证，未认证)；
	private Integer onLine; //是否在线(0：否， 1：是)；
	private String openChatTime;//打开对话框的时间
	public String getOpenChatTime() {
		return openChatTime;
	}
	public void setOpenChatTime(String openChatTime) {
		this.openChatTime = openChatTime;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAgentProductName() {
		return agentProductName;
	}
	public void setAgentProductName(String agentProductName) {
		this.agentProductName = agentProductName;
	}
	public String getDeveloperProductName() {
		return developerProductName;
	}
	public void setDeveloperProductName(String developerProductName) {
		this.developerProductName = developerProductName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = "400-8986868";
	}
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getImPassword() {
		return imPassword;
	}
	
	public void setImPassword(String imPassword) {
		this.imPassword = imPassword;
	}
	
	public Integer getOnLine() {
		return onLine;
	}
	
	public void setOnLine(Integer onLine) {
		this.onLine = onLine;
	}
	
}
