package com.dsj.modules.solr.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class CommonIndexFiled implements Serializable {

	/**
	 * 二手房sorl字段
	 */
	private static final long serialVersionUID = 1L;
	@Field("id")
	private String  id;
	@Field("name")
	private String name;
	@Field("type")
	private Integer type;//小区类型 1地区 2商圈 3二手房小区            新房:1三级地区  2商圈 3楼盘名称
	@Field("fullPinyin")
	private String fullPinyin; 
	@Field("jianPin")
	private String jianPin;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFullPinyin() {
		return fullPinyin;
	}
	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}
	public String getJianPin() {
		return jianPin;
	}
	public void setJianPin(String jianPin) {
		this.jianPin = jianPin;
	}
	
	
}























