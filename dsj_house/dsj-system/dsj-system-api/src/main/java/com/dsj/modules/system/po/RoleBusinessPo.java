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
public class RoleBusinessPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long roleId;		// 角色ID
	private String businessId;		// 数据权限角色集合
	
	public RoleBusinessPo() {
		super();
	}

	public RoleBusinessPo(Long id){
		this();
	}
	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
}