package com.dsj.modules.oldhouse.vo;

import java.io.Serializable;

import com.dsj.common.enums.FloorTypeEnum;

public class OldHouseRecommendVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String imageUrl;
	private Double price;
	private Double buildArea;
	private Double unitPrice;
	private Long dicId;
	private String dicName;
	
	private Integer hall;		// 厅
	private Integer room;		// 室
	private Integer floorNum;		// 总楼层
	private Integer floorType;
	
	private String floorTypeName;
	private String buildYear;		// 建造年代
	private String features;
	
	private String areaName3;
	
	private String featureNames;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	public Long getDicId() {
		return dicId;
	}
	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public Integer getHall() {
		return hall;
	}
	public void setHall(Integer hall) {
		this.hall = hall;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public Integer getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}
	public Integer getFloorType() {
		return floorType;
	}
	public void setFloorType(Integer floorType) {
		this.floorType = floorType;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getAreaName3() {
		return areaName3;
	}
	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}
	public String getFloorTypeName() {
		if(floorType!=null){
			return FloorTypeEnum.getEnum(floorType).getDesc();
		}
		return floorTypeName;
	}
	public void setFloorTypeName(String floorTypeName) {
		this.floorTypeName = floorTypeName;
	}
	public String getFeatureNames() {
		return featureNames;
	}
	public void setFeatureNames(String featureNames) {
		this.featureNames = featureNames;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}
