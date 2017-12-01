package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public class RolePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String nameCode;		// 标识
	private String name;		// 角色名称
	private Date updateTime;		// 更新时间
	
	public RolePo() {
		super();
	}

	public RolePo(Long id){
		this();
	}
	

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}