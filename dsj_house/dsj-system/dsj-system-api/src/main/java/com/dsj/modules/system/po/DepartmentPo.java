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
public class DepartmentPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String deptName;		// 部门名
	private Long parentId;		// 父节点
	private Integer deleteFlag;		// 删除标记
	private Integer createPerson;		// create_person
	private String deptDuty;		// 部门角色
	private String deptMess;		// 部门描述
	private String prefix;		// 部门前缀
	
	public DepartmentPo() {
		super();
	}

	public DepartmentPo(Long id){
		this();
	}
	

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}
	
	public String getDeptDuty() {
		return deptDuty;
	}

	public void setDeptDuty(String deptDuty) {
		this.deptDuty = deptDuty;
	}
	
	public String getDeptMess() {
		return deptMess;
	}

	public void setDeptMess(String deptMess) {
		this.deptMess = deptMess;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}