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
public class BusinessDeptPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long roleId;		// 角色ID
	private String deptId;		// 本人：a,本部：b,全部：c，跨联iD：部门ID逗号隔开
	
	public BusinessDeptPo() {
		super();
	}

	public BusinessDeptPo(Long id){
		this();
	}
	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}