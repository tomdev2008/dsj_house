package com.dsj.modules.rent.vo;

import com.dsj.common.entity.BaseEntity;

public class WarrantOriginVo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String linkAddress;
	private String picture;
	private String label;
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
