package com.dsj.modules.system.vo;

import java.io.Serializable;
import java.util.List;

public class EasyuiSelectTreeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private String state;
	private Boolean checked;
	private List<EasyuiSelectTreeVo> children;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<EasyuiSelectTreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<EasyuiSelectTreeVo> children) {
		this.children = children;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
