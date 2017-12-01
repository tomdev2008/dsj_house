package com.dsj.modules.solr.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class AreaLatLngGroupVo   implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String lat;//维度
	private String  lng;//精度 dimension
	private String id;//地区id 或者 商圈id 小区id
	private Integer count;
	private String name;
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
