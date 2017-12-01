package com.dsj.modules.oldhouse.vo;

import com.dsj.common.entity.BaseEntity;
import com.dsj.common.enums.FloorTypeEnum;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-28 14:44:14.
 * @版本: 1.0 .
 */
public class OldHouseMasterAppVo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	

	private Long dicId;		// 楼盘ID

	private Integer hall;		// 厅
	private Integer room;		// 室
	private Integer toilet;		// 卫
	private Integer kitchen;		// 厨
	private String houseType;		// 房屋类型
	private Integer renovation;		// 装修情况
	private Integer floorNum;		// 总楼层
	private Integer floorType;
	private String dicName;	
	private Double buildArea;		// 建筑面积
	private String buildYear;		// 建造年代

	private Double payments;		// 首付
	private Double price;		// 售价
	private String features;		// 特色标签 逗号拼接
	private String title;		// 标题,推荐标语
	private String sellingPoint;		// 核心卖点
	private String houseDetail;		// 房源详情
	private String owneMentality;		// 业主心态
	private String villageMatching;		// 小区配套
	private String serviceIntro;		// 服务介绍
	private Integer balcony;		// 阳
	private Double builtUp;		// 建筑面积
	private Integer orientations;		// 朝向
	private String houseYear;		// 房屋年限
	
	private Double unitPrice;		// 单价
	private Double monthpay;		// 月供
	
	private Integer companyType;
	private String companyTypes;

	private Long userId;
	
	private String houseTypeName;
	
	private String renovationName;		// 装修情况
	private String featureNames;		// 特色标签 逗号拼接
	//转化
	private String floorTypeName;
	public OldHouseMasterAppVo() {
		super();
	}


	public Long getDicId() {
		return dicId;
	}


	public void setDicId(Long dicId) {
		this.dicId = dicId;
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


	public Integer getToilet() {
		return toilet;
	}


	public void setToilet(Integer toilet) {
		this.toilet = toilet;
	}


	public Integer getKitchen() {
		return kitchen;
	}


	public void setKitchen(Integer kitchen) {
		this.kitchen = kitchen;
	}


	public String getHouseType() {
		return houseType;
	}


	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}


	public Integer getRenovation() {
		return renovation;
	}


	public void setRenovation(Integer renovation) {
		this.renovation = renovation;
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


	public Double getPayments() {
		return payments;
	}


	public void setPayments(Double payments) {
		this.payments = payments;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
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


	public String getSellingPoint() {
		return sellingPoint;
	}


	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
	}


	public String getHouseDetail() {
		return houseDetail;
	}


	public void setHouseDetail(String houseDetail) {
		this.houseDetail = houseDetail;
	}


	public String getOwneMentality() {
		return owneMentality;
	}


	public void setOwneMentality(String owneMentality) {
		this.owneMentality = owneMentality;
	}


	public String getVillageMatching() {
		return villageMatching;
	}


	public void setVillageMatching(String villageMatching) {
		this.villageMatching = villageMatching;
	}


	public String getServiceIntro() {
		return serviceIntro;
	}


	public void setServiceIntro(String serviceIntro) {
		this.serviceIntro = serviceIntro;
	}


	public Integer getBalcony() {
		return balcony;
	}


	public void setBalcony(Integer balcony) {
		this.balcony = balcony;
	}


	public Double getBuiltUp() {
		return builtUp;
	}


	public void setBuiltUp(Double builtUp) {
		this.builtUp = builtUp;
	}


	public Integer getOrientations() {
		return orientations;
	}


	public void setOrientations(Integer orientations) {
		this.orientations = orientations;
	}


	public String getHouseYear() {
		return houseYear;
	}


	public void setHouseYear(String houseYear) {
		this.houseYear = houseYear;
	}


	public Double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Double getMonthpay() {
		return monthpay;
	}


	public void setMonthpay(Double monthpay) {
		this.monthpay = monthpay;
	}


	public Integer getCompanyType() {
		return companyType;
	}


	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}


	public String getCompanyTypes() {
		return companyTypes;
	}


	public void setCompanyTypes(String companyTypes) {
		this.companyTypes = companyTypes;
	}

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFloorTypeName() {
	
		return floorTypeName;
	}

	public void setFloorTypeName(String floorTypeName) {
		this.floorTypeName = floorTypeName;
	}


	public String getDicName() {
		return dicName;
	}


	public void setDicName(String dicName) {
		this.dicName = dicName;
	}


	public String getHouseTypeName() {
		return houseTypeName;
	}


	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}


	public String getRenovationName() {
		return renovationName;
	}


	public void setRenovationName(String renovationName) {
		this.renovationName = renovationName;
	}


	public String getFeatureNames() {
		return featureNames;
	}


	public void setFeatureNames(String featureNames) {
		this.featureNames = featureNames;
	}
	
	
	
}