package com.dsj.modules.solr.vo.newHouse;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class NewHouseIndexFiled implements Serializable {

	/**
	 * newHouse-sorl字段
	 */
	private static final long serialVersionUID = 1L;
	@Field("id")
	private String  id;
	
	@Field("title")
	private String newsId;		// 动态标题
	
	
	@Field("areaCode1")
	private String areaCode1;//地区编码1
	
	@Field("name")
	private String name;//楼盘名称
	
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
	
	@Field("title")
	private String title;		// 动态标题
	
	@Field("imageUrl")
	private String imageUrl; //列表封面图
	
	@Field("rooms")
	private String rooms; //室
	
	@Field("dicTraitName")
	private String dicTraitName;	// 楼盘特色id逗号拼接
	
	@Field("featuresName")
	private String featuresName; // 楼盘特色名称逗号拼接
	
	@Field("wyTypeStr")
	private String wyTypeStr; //物业
	
	@Field("roomNames")
	private String roomNames; //室
	
	@Field("authTime")
	private Date authTime; //上架时间
	
	@Field("status")
	private Integer status; //新房状态
	
	@Field("isSaleOut")
	private Integer isSaleOut; //是否售罄
	
	@Field("hasPrice")
	private Integer hasPrice; //是否有价格 1单价 2总价 3周边均价  4无价格
	
	@Field("address")
	private String address; //新房地址
	
	@Field("mobile")
	private String mobile; //电话
	
	@Field("pcMobile")
	private String pcMobile;
	@Field("wapMobile")
	private String wapMobile;
	@Field("appMobile")
	private String appMobile;
	
	@Field("agent")
	private String agent; //经纪人字符串(id+头像+名称+","+id+头像+名称);
	
	@Field("dimension")
	private String dimension;//楼盘经度
	@Field("accuracy")
	private String  accuracy;//楼盘纬度
	
	@Field("areaDimension")
	private String areaDimension;//地区经度
	@Field("areaAccuracy")
	private String  areaAccuracy;//地区维度
	
	@Field("referencePriceMin")
	private Double referencePriceMin;//单价
	@Field("referencePriceMax")
	private Double referencePriceMax;//单价
	
	@Field("totalPriceMin")
	private Double totalPriceMin;//总价
	@Field("totalPriceMax")
	private Double totalPriceMax;//总价
	
	@Field("aroundPriceMin")
	private Double aroundPriceMin;//周边单价
	@Field("aroundPriceMax")
	private Double aroundPriceMax;//周边单价
	
	@Field("subwayline")
	private String subwayline;//地铁线
	@Field("subway")
	private String  subway;//地铁站
	
	//前top20
	@Field("orderNum")
	private Integer orderNum;
	
	@Field("tradingAreaName")
	private String tradingAreaName;//商圈名称
	
	@Field("tradingAreaId")
	private Long tradingAreaId;//商圈编码
	
	private String tradingAreaCode;//商圈编码
	
	
	
	public Long getTradingAreaId() {
		return tradingAreaId;
	}
	public void setTradingAreaId(Long tradingAreaId) {
		this.tradingAreaId = tradingAreaId;
	}
	public Integer getHasPrice() {
		return hasPrice;
	}
	public void setHasPrice(Integer hasPrice) {
		this.hasPrice = hasPrice;
	}
	public Integer getIsSaleOut() {
		return isSaleOut;
	}
	public void setIsSaleOut(Integer isSaleOut) {
		this.isSaleOut = isSaleOut;
	}
	public String getFeaturesName() {
		return featuresName;
	}
	public void setFeaturesName(String featuresName) {
		this.featuresName = featuresName;
	}
	public String getTradingAreaCode() {
		return tradingAreaCode;
	}
	public void setTradingAreaCode(String tradingAreaCode) {
		this.tradingAreaCode = tradingAreaCode;
	}
	public String getPcMobile() {
		return pcMobile;
	}
	public void setPcMobile(String pcMobile) {
		this.pcMobile = pcMobile;
	}
	public String getWapMobile() {
		return wapMobile;
	}
	public void setWapMobile(String wapMobile) {
		this.wapMobile = wapMobile;
	}
	public String getAppMobile() {
		return appMobile;
	}
	public void setAppMobile(String appMobile) {
		this.appMobile = appMobile;
	}
	public String getWyTypeStr() {
		return wyTypeStr;
	}
	public void setWyTypeStr(String wyTypeStr) {
		this.wyTypeStr = wyTypeStr;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getRoomNames() {
		return roomNames;
	}
	public void setRoomNames(String roomNames) {
		this.roomNames = roomNames;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getAreaName1() {
		return areaName1;
	}
	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}
	public String getAreaCode2() {
		return areaCode2;
	}
	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}
	public String getAreaName2() {
		return areaName2;
	}
	public void setAreaName2(String areaName2) {
		this.areaName2 = areaName2;
	}
	public String getAreaCode3() {
		return areaCode3;
	}
	public void setAreaCode3(String areaCode3) {
		this.areaCode3 = areaCode3;
	}
	public String getAreaName3() {
		return areaName3;
	}
	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}
	public String getDicTraitName() {
		return dicTraitName;
	}
	public void setDicTraitName(String dicTraitName) {
		this.dicTraitName = dicTraitName;
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
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public Date getAuthTime() {
		return authTime;
	}
	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public Double getReferencePriceMin() {
		return referencePriceMin;
	}
	public void setReferencePriceMin(Double referencePriceMin) {
		this.referencePriceMin = referencePriceMin;
	}
	public Double getReferencePriceMax() {
		return referencePriceMax;
	}
	public void setReferencePriceMax(Double referencePriceMax) {
		this.referencePriceMax = referencePriceMax;
	}
	public Double getTotalPriceMin() {
		return totalPriceMin;
	}
	public void setTotalPriceMin(Double totalPriceMin) {
		this.totalPriceMin = totalPriceMin;
	}
	public Double getTotalPriceMax() {
		return totalPriceMax;
	}
	public void setTotalPriceMax(Double totalPriceMax) {
		this.totalPriceMax = totalPriceMax;
	}
	public Double getAroundPriceMin() {
		return aroundPriceMin;
	}
	public void setAroundPriceMin(Double aroundPriceMin) {
		this.aroundPriceMin = aroundPriceMin;
	}
	public Double getAroundPriceMax() {
		return aroundPriceMax;
	}
	public void setAroundPriceMax(Double aroundPriceMax) {
		this.aroundPriceMax = aroundPriceMax;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
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
	public String getTradingAreaName() {
		return tradingAreaName;
	}
	public void setTradingAreaName(String tradingAreaName) {
		this.tradingAreaName = tradingAreaName;
	}
	
	
	

	
}























