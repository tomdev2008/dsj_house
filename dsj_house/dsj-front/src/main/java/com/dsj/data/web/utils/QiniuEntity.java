package com.dsj.data.web.utils;


/***
 * 七牛云存储相关类
 * @author lihejia
 *
 */
public class QiniuEntity {
	
	private String uptoken;
	
	private String domain;
	
	private String userName;
	
	private QiniuEntity() {}
	
	private static class Inner {
		static QiniuEntity instance = new QiniuEntity();
	}
	
	public static QiniuEntity getInstance() {
		return Inner.instance;
	}
	
	public String getUptoken() {
		return uptoken;
	}
	
	public void setUptoken(String uptoken) {
		this.uptoken = uptoken;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
