package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 17:49:26.
 * @版本: 1.0 .
 */
public class UserPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String realname;       //真实姓名
	private String username;		// 用户名
	private String password;		// 密码
	private String avatar;          //头像
	private String salt;//掩
	private Integer userType;		// 用户类型
	private Integer status;		// 审核状态
	private Date lastloginTime;		// 最后一次登录时间
	private String ip;		// 最后一次登录ip
	private Integer markFlag;		// 是否激活im
	private Integer loginFlag;		// 是否允许登录平台
	private Integer imFlag;		// 是否允许登录im
	private Integer delFlag;		// 删除标记
	private Integer updatePerson;		// 修改人
	private Date updateTime;		// 修改时间
	private Integer createPerson;		// 创建人
	private String phone;
	private String imPassword;  //环信密码
	private Long eveloperId;
	private Long agentId;
	private Long propertyId;
	private String openId;
	private Integer type;
	
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getLastloginTime() {
		return lastloginTime;
	}

	public void setLastloginTime(Date lastloginTime) {
		this.lastloginTime = lastloginTime;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getMarkFlag() {
		return markFlag;
	}

	public void setMarkFlag(Integer markFlag) {
		this.markFlag = markFlag;
	}
	
	public Integer getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	public Integer getImFlag() {
		return imFlag;
	}

	public void setImFlag(Integer imFlag) {
		this.imFlag = imFlag;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public Integer getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Integer updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPhone() {
		return phone;
	}

	public String getImPassword() {
		return imPassword;
	}

	public void setImPassword(String imPassword) {
		this.imPassword = imPassword;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getEveloperId() {
		return eveloperId;
	}

	public void setEveloperId(Long eveloperId) {
		this.eveloperId = eveloperId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	
	
}