package com.dsj.modules.solr.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class ErshoufangIndexFiled implements Serializable {

	/**
	 * 二手房sorl字段
	 */
	private static final long serialVersionUID = 1L;
	@Field("id")
	private String  id;
	@Field("areaCode1")
	private String areaCode1;//地区编码1
	
	@Field("areaName1")
	private String areaName1;//地区名称
	
	@Field("areaCode2")
	private String areaCode2;//地区编码2
	
	@Field("areaName2")
	private String areaName2;//地区名称
	
	@Field("areaCode3")
	private String areaCode3;//地区编码3
	
	@Field("areaName3")
	private String areaName3;//地区名称
	
	
	@Field("tradingAreaId")
	private Long tradingAreaId;//'商圈ID'
	
	@Field("tradingAreaName")
	private String tradingAreaName;//'商圈ID'
	
	@Field("price")
	private Double price;		// 售价
	@Field("floorType")
	private Integer floorType;		// 楼层类型
	
	@Field("floorNum")
	private Integer floorNum;		// 楼层
	
	@Field("companyTypes")
	private String companyTypes;//来源类型
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
	@Field("dicId")
	private Long dicId;//小区id
	
	@Field("unitPrice")
	private Double unitPrice;//单价
	
	@Field("updateTime")
	private Date updateTime;
	@Field("dimension")
	private String dimension;//楼盘维度
	@Field("accuracy")
	private String  accuracy;//楼盘维度
	
	@Field("areaDimension")
	private String areaDimension;//地区维度
	@Field("areaAccuracy")
	private String  areaAccuracy;//地区维度
	
	@Field("tradeDimension")
	private String tradeDimension;//商圈维度
	@Field("tradeAccuracy")
	private String  tradeAccuracy;//商圈维度
	
	
	@Field("subwayline")
	private String subwayline;//地铁线
	@Field("subway")
	private String  subway;//地铁站
	
	@Field("companyTypeNames")
	private String companyTypeNames;//来源类型
	

	@Field("featuresName")
	private String featuresName;	// 特色标签 逗号拼接
	
	@Field("ico")
	private String ico;
	
	@Field("wyType")
	private Integer houseType;
	
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getAreaName1() {
		return areaName1;
	}
	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}
	public String getAreaName2() {
		return areaName2;
	}
	public void setAreaName2(String areaName2) {
		this.areaName2 = areaName2;
	}
	public String getAreaName3() {
		return areaName3;
	}
	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}
	public Long getTradingAreaId() {
		return tradingAreaId;
	}
	public void setTradingAreaId(Long tradingAreaId) {
		this.tradingAreaId = tradingAreaId;
	}
	public String getTradingAreaName() {
		return tradingAreaName;
	}
	public void setTradingAreaName(String tradingAreaName) {
		this.tradingAreaName = tradingAreaName;
	}
	public Integer getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
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
	public String getAreaDimension() {
		return areaDimension;
	}
	public void setAreaDimension(String areaDimension) {
		this.areaDimension = areaDimension;
	}
	public String getAreaAccuracy() {
		return areaAccuracy;
	}
	public void setAreaAccuracy(String areaAccuracy) {
		this.areaAccuracy = areaAccuracy;
	}
	public String getTradeDimension() {
		return tradeDimension;
	}
	public void setTradeDimension(String tradeDimension) {
		this.tradeDimension = tradeDimension;
	}
	public String getTradeAccuracy() {
		return tradeAccuracy;
	}
	public void setTradeAccuracy(String tradeAccuracy) {
		this.tradeAccuracy = tradeAccuracy;
	}
	public Long getDicId() {
		return dicId;
	}
	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}
	
	public String getSubwayline() {
		return subwayline;
	}
	public void setSubwayline(String subwayline) {
		this.subwayline = subwayline;
	}
	public String getSubway() {
		return subway;
	}
	public void setSubway(String subway) {
		this.subway = subway;
	}
	public String getCompanyTypeNames() {
		return companyTypeNames;
	}
	public void setCompanyTypeNames(String companyTypeNames) {
		this.companyTypeNames = companyTypeNames;
	}
	public String getFeaturesName() {
		return featuresName;
	}
	public void setFeaturesName(String featuresName) {
		this.featuresName = featuresName;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public Integer getHouseType() {
		return houseType;
	}
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	
}























