package com.dsj.modules.fw.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class FwDetailCommentVo implements Serializable{
	private String Label;
	private String content;
	private String score;
	private String username;
	private String avatar;
	private String realname;
	private Integer orderCount;
	private String haoPingLv;
	private Integer deal;
	private String[] lableList;
	private String createTime;
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String[] getLableList() {
		return lableList;
	}
	public void setLableList(String[] lableList) {
		this.lableList = lableList;
	}
	public Integer getDeal() {
		return deal;
	}
	public void setDeal(Integer deal) {
		this.deal = deal;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getUsername() {
		if(StringUtils.isNotBlank(this.username)){
			username = this.username.substring(0, 3)+"****"+this.username.substring(3, 7);
			return username;
		}else{
			return username;
		}
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public String getHaoPingLv() {
		return haoPingLv;
	}
	public void setHaoPingLv(String haoPingLv) {
		this.haoPingLv = haoPingLv;
	}


	
		
}
