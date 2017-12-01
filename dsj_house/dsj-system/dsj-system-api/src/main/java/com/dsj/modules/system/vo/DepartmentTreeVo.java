package com.dsj.modules.system.vo;

import java.io.Serializable;
import java.util.List;

public class DepartmentTreeVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String deptName;
	private String deptMess;
	private Long parentId;		// 父节点
	List<DepartmentTreeVo> children;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptMess() {
		return deptMess;
	}
	public void setDeptMess(String deptMess) {
		this.deptMess = deptMess;
	}
	public List<DepartmentTreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<DepartmentTreeVo> children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}	
