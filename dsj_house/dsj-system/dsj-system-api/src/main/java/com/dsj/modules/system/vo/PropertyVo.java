package com.dsj.modules.system.vo;

import java.io.Serializable;

import com.dsj.modules.system.po.PropertyPo;

/**
 * @author gaocj
 *
 */
public class PropertyVo extends PropertyPo implements Serializable{
	
	//添加字段
	private Integer status;
	private String password;
	private String realName;
	private String userName;
	private String haoPingLv;
	private Integer orderCount;
	private String phone;//400电话

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	
	
	

}
