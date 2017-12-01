package com.dsj.modules.solr.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

import com.dsj.common.enums.FloorTypeEnum;

public class ErshoufangSolrVo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field("id")
	private String  id;
	@Field("areaCode1")
	private String areaCode1;//地区编码1
	@Field("areaCode2")
	private String areaCode2;//地区编码2
	@Field("areaCode3")
	private String areaCode3;//地区编码3
	
	@Field("areaName3")
	private String areaName3;//地区名称
	
	@Field("price")
	private Double price;		// 售价
	@Field("floorType")
	private Integer floorType;		// 楼层
	
	private String floorTypeName;		// 楼层
	
	@Field("companyTypes")
	private String companyTypes;//来源类型
	
	@Field("companyTypeNames")
	private String companyTypeNames;//来源类型
	
	private String[] companyTypeNamesArr;
	
	@Field("orientations")
	private Integer orientations;//朝向
	@Field("renovation")
	private Integer renovation;//装修情况
	@Field("buildArea")
	private Double buildArea;		// 建筑面积
	@Field("buildYear")
	private String buildYear;		// 建造年代
	@Field("buildYearDate")
	private Date buildYearDate;
	
	@Field("features")
	private String features;	// 特色标签 逗号拼接
	
	@Field("featuresName")
	private String featuresName;	// 特色标签 逗号拼接
	
	private String[] featuresNameArr;
	
	@Field("title")
	private String title;		// 标题,推荐标语
	@Field("imageUrl")
	private String imageUrl;
	@Field("hall")
	private Integer hall;//厅
	@Field("room")
	private Integer room; //室
	@Field("dicName")
	private String dicName;//小区名称
	@Field("tradingAreaName")
	private String tradingAreaName;//商圈名称
	@Field("unitPrice")
	private Double unitPrice;//单价
	@Field("floorNum")
	private Integer floorNum;		// 楼层
	@Field("ico")
	private String ico;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public String getAreaCode3() {
		return areaCode3;
	}

	public void setAreaCode3(String areaCode3) {
		this.areaCode3 = areaCode3;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getFloorType() {
		return floorType;
	}

	public void setFloorType(Integer floorType) {
		this.floorType = floorType;
	}

	public String getCompanyTypes() {
		return companyTypes;
	}

	public void setCompanyTypes(String companyTypes) {
		this.companyTypes = companyTypes;
	}

	public Integer getOrientations() {
		return orientations;
	}

	public void setOrientations(Integer orientations) {
		this.orientations = orientations;
	}

	public Integer getRenovation() {
		return renovation;
	}

	public void setRenovation(Integer renovation) {
		this.renovation = renovation;
	}

	public Double getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public Date getBuildYearDate() {
		return buildYearDate;
	}

	public void setBuildYearDate(Date buildYearDate) {
		this.buildYearDate = buildYearDate;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
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

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public String getTradingAreaName() {
		return tradingAreaName;
	}

	public void setTradingAreaName(String tradingAreaName) {
		this.tradingAreaName = tradingAreaName;
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

	public String getFeaturesName() {
		return featuresName;
	}

	public void setFeaturesName(String featuresName) {
		this.featuresName = featuresName;
	}

	public String getCompanyTypeNames() {
		return companyTypeNames;
	}

	public void setCompanyTypeNames(String companyTypeNames) {
		this.companyTypeNames = companyTypeNames;
	}

	public String[] getCompanyTypeNamesArr() {
		if(StringUtils.isNotBlank(companyTypeNames)){
			return companyTypeNames.split(",");
		}
		return companyTypeNamesArr;
	}

	public void setCompanyTypeNamesArr(String[] companyTypeNamesArr) {
		this.companyTypeNamesArr = companyTypeNamesArr;
	}

	public String[] getFeaturesNameArr() {
		if(StringUtils.isNotBlank(featuresName)){
			return featuresName.split(",");
		}
		return featuresNameArr;
	}

	public void setFeaturesNameArr(String[] featuresNameArr) {
		this.featuresNameArr = featuresNameArr;
	}

	public String getAreaName3() {
		return areaName3;
	}

	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}
	
	
}
