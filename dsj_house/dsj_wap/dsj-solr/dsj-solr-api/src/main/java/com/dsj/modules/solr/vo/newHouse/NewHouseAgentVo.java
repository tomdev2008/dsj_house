package com.dsj.modules.solr.vo.newHouse;

import java.io.Serializable;

public class NewHouseAgentVo implements Serializable {

	private String id;
	private String username;//用户姓名
	private String realname;//真实姓名
	private String headImgUrl;//头像
	private String mobile;//分机号
	private String jgarea;//精耕区域
	private String workyear;//工作经验
	private String companyname;//公司名称
	private String djtb;//等级图标
	private String djch;//等级称呼
	private Integer islz;//是否是楼主
	
	
	public Integer getIslz() {
		return islz;
	}
	public void setIslz(Integer islz) {
		this.islz = islz;
	}
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
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
}
