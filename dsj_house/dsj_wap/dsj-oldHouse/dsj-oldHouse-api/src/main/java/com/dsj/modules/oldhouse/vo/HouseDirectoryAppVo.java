package com.dsj.modules.oldhouse.vo;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.楼盘字典
 * @作者: kk.
 * @创建时间: 2017-07-16 08:34:03.
 * @版本: 1.0 .
 */
public class HouseDirectoryAppVo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	

	private String sprayName;		// 喷涂楼盘名称

	private String oldName;		// 楼盘曾用名
	private String developers;		// 开发商
	private String phone;		// 400电话
	private String address;		// 楼盘地址
	private String saleAddress;		// 售楼处地址
	private String describe;		// 楼盘简介

	private String dicTrait;		// 楼盘特点
	

	private Integer landYear;		// 土地使用年限
	private String parkingSpace;		// 车位比

	private String parkNumber;		// 车位数
	private Integer houseNum;		// 房屋总数
	private Integer floorNum;		// 楼栋总数

	private String occupyArea;		// 占地面积
	private String builtUp;		// 总建筑面积
	private String plotRatio;		// 容积率
	private Double greenRate;		// 绿化率

	private Long heatingMode;		// 供暖方式
	private String propertyFee;		// 物业费
	private Integer averagePrice;		// 小区均价
	private String imageUrl;
	
	private Integer achYear;		// 建筑年代
	
	private String dimension;		// 楼盘维度
	private String accuracy;		// 楼盘精度
	public HouseDirectoryAppVo() {
		super();
	}

	public HouseDirectoryAppVo(Long id){
		this();
	}

	public String getSprayName() {
		return sprayName;
	}

	public void setSprayName(String sprayName) {
		this.sprayName = sprayName;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSaleAddress() {
		return saleAddress;
	}

	public void setSaleAddress(String saleAddress) {
		this.saleAddress = saleAddress;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDicTrait() {
		return dicTrait;
	}

	public void setDicTrait(String dicTrait) {
		this.dicTrait = dicTrait;
	}



	public Integer getLandYear() {
		return landYear;
	}

	public void setLandYear(Integer landYear) {
		this.landYear = landYear;
	}

	public String getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(String parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	public String getParkNumber() {
		return parkNumber;
	}

	public void setParkNumber(String parkNumber) {
		this.parkNumber = parkNumber;
	}

	public Integer getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	

	public String getOccupyArea() {
		return occupyArea;
	}

	public void setOccupyArea(String occupyArea) {
		this.occupyArea = occupyArea;
	}

	public String getBuiltUp() {
		return builtUp;
	}

	public void setBuiltUp(String builtUp) {
		this.builtUp = builtUp;
	}

	public String getPlotRatio() {
		return plotRatio;
	}

	public void setPlotRatio(String plotRatio) {
		this.plotRatio = plotRatio;
	}

	public Double getGreenRate() {
		return greenRate;
	}

	public void setGreenRate(Double greenRate) {
		this.greenRate = greenRate;
	}

	public Long getHeatingMode() {
		return heatingMode;
	}

	public void setHeatingMode(Long heatingMode) {
		this.heatingMode = heatingMode;
	}

	public String getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(String propertyFee) {
		this.propertyFee = propertyFee;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Integer averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Integer getAchYear() {
		return achYear;
	}

	public void setAchYear(Integer achYear) {
		this.achYear = achYear;
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