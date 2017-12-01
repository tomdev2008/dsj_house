package com.dsj.modules.rent.vo;

import java.io.Serializable;

/**
 * 租房房源地图查询基本类
 */
public class RentCountMapInfoVo implements Serializable{
	
	private Integer count;//房源总数
	private Long id ;	//当前标注id
	private String name ;//当前标注名称
	private String dimension ;//纬度
	private String accuracy ;//经度
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	
}
