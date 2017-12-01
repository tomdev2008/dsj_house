package com.dsj.modules.comment.vo;

import com.dsj.modules.comment.po.HouseNewsPo;

public class HouseNewsVo extends HouseNewsPo{

	private String auditStatusName;		// 审核状态
	private String upDownLineName;		// 上下线
	private String userName; //发布人
	private String houseName; //楼盘名称
	
	private String developersName;//开发商名称

	private String isTure;//楼盘是否上架(包含已删除)1未上架 2已上架 3已下架 4已删除'
	private String isTureName;//楼盘是否上架(包含已删除)1未上架 2已上架 3已下架 4已删除'

	private String companyName;//发布人公司
	private String shortName;//发布人公司简写
	private String userTypeName;//用户类型名称
	
	public String getAuditStatusName() {
		return auditStatusName;
	}

	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}

	public String getUpDownLineName() {
		return upDownLineName;
	}

	public void setUpDownLineName(String upDownLineName) {
		this.upDownLineName = upDownLineName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getDevelopersName() {
		return developersName;
	}

	public void setDevelopersName(String developersName) {
		this.developersName = developersName;
	}

	public String getIsTure() {
		return isTure;
	}

	public void setIsTure(String isTure) {
		this.isTure = isTure;
	}

	public String getIsTureName() {
		return isTureName;
	}

	public void setIsTureName(String isTureName) {
		this.isTureName = isTureName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
}
