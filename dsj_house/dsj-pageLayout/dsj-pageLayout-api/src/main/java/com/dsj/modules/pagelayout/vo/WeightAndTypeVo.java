package com.dsj.modules.pagelayout.vo;

import java.io.Serializable;

public class WeightAndTypeVo implements Serializable{
	private Integer type;
	private String name;
	private Integer weight;
	private Integer pageFirst;
	public Integer getPageFirst() {
		return pageFirst;
	}
	public void setPageFirst(Integer pageFirst) {
		this.pageFirst = pageFirst;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
