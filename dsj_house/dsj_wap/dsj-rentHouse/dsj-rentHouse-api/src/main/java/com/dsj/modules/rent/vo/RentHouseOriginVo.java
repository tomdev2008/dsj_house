package com.dsj.modules.rent.vo;

import java.util.Date;

import com.dsj.modules.rent.po.RentHouseOriginPo;

public class RentHouseOriginVo extends RentHouseOriginPo {
	private String cardName;// 证载楼盘名称 （小区名称）
	private String areaName1;		// 地区名1
	private String areaName2;		// 地区名2
	private String areaName3;		// 地区名3
	private String tradingAreaName;	// 商圈名称
	private String statusName;	// 状态名称
	private String rentTypeName;	// 出租类型名称
	private String address;           //小区地址
	private String sprayName; //小区（楼盘名称）
	private String dimension; //纬度
	private String accuracy; //经度
	private String agentName; //经纪人名称
	private String avatar; //经纪人头像
	private String companyName; //经纪人公司
	private String agentPhone; //经纪人电话
	private Long pcRentId;
	private Date lookTime;
	private String payTypeName;
	private Integer objectId;//关注用的id
	
	private Integer recommend;//是否推荐  1推荐，2不推荐
	private Integer show;//是否展示首页  1展示，2不展示
	
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public Date getLookTime() {
		return lookTime;
	}
	public void setLookTime(Date lookTime) {
		this.lookTime = lookTime;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
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
	public String getTradingAreaName() {
		return tradingAreaName;
	}
	public void setTradingAreaName(String tradingAreaName) {
		this.tradingAreaName = tradingAreaName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getRentTypeName() {
		return rentTypeName;
	}
	public void setRentTypeName(String rentTypeName) {
		this.rentTypeName = rentTypeName;
	}
	public String getSprayName() {
		return sprayName;
	}
	public void setSprayName(String sprayName) {
		this.sprayName = sprayName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPcRentId() {
		return pcRentId;
	}
	public void setPcRentId(Long pcRentId) {
		this.pcRentId = pcRentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
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
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getShow() {
		return show;
	}
	public void setShow(Integer show) {
		this.show = show;
	}
	
	
}
