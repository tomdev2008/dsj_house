package com.dsj.modules.newhouse.vo;

import java.io.Serializable;

public class NewHouseAgentFrontVo implements Serializable {

	private String realName;//真实姓名
	
	private String userName;//真实姓名
	
	private String mobile;//分机号
	
	private String label;//经纪人标签
	
	private String imgUrl;//头像
	
	private Long userId;//经纪人用户id
	
	private String shortName;//公司简称

	private String jgarea;//精耕区域
	
	private String workyear;//工作经验
	
	private String[] labels;
	

	private Integer isDuty;//是否楼盘维护人 0:否 1:是',

	private String djtb;//等级图标

	private String djch;//等级称呼

	
	public String getDjtb() {
		return djtb;
	}

	public void setDjtb(String djtb) {
		this.djtb = djtb;
	}

	public String getDjch() {
		return djch;
	}

	public void setDjch(String djch) {
		this.djch = djch;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String getJgarea() {
		return jgarea;
	}

	public void setJgarea(String jgarea) {
		this.jgarea = jgarea;
	}

	public String getWorkyear() {
		return workyear;
	}

	public void setWorkyear(String workyear) {
		this.workyear = workyear;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsDuty() {
		return isDuty;
	}

	public void setIsDuty(Integer isDuty) {
		this.isDuty = isDuty;
	}
	
}
