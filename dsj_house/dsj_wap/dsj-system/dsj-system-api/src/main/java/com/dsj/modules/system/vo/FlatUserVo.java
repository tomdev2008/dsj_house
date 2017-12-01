package com.dsj.modules.system.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.dsj.modules.system.po.FlatUserPo;

public class FlatUserVo extends FlatUserPo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;	//用户名
	private String password;
	private Integer status;		// 审核状态
	private Integer delFlag;		// 删除标记
	private Long createPerson;   //创建人
	private Long updatePerson;   //修改人
	private Date createTime;	//创建时间
	private Date updateTime;	//修改日期
	
	//查询
	private String fullName;	//地域全名
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public FlatUserVo() {}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Long getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Long updatePerson) {
		this.updatePerson = updatePerson;
	}

	public Long getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Long createPerson) {
		this.createPerson = createPerson;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
