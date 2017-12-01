package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:21.
 * @版本: 1.0 .
 */
public class HouseAlikeCommunityTempPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;		// 小区名称
	private String nickName;		// 小区别名
	private String averagePrice;		// 均价
	private String builtYear;		// 建成时间
	private String buildingType;		// 建筑类型
	private String buildingNum;		// 楼栋数
	private String houseTypeNum;		// 户型数
	private String saleNumber;		// 在售房源数
	private String propertyFee;		// 物业费
	private String propertyCompany;		// 物业公司
	private String totalHouse;		// 总户数
	private String store;		// 所属门店
	private String originCommunityId;		// 原小区ID
	private String developer;		// 开发商
	private String accuracy;		// 楼盘精度
	private String dimension;		// 楼盘维度
	private String resblockPosition;		// 坐标
	private String pageUrl;		// 所属页面
	private String company;		// 所属公司
	private Long lianjiaId;		// 对于链家数据ID
	private String areaCode1;		// 省地区
	private String areaName1;		// area_name1
	private String areaCode2;		// area_code2
	private String areaName2;		// area_name2
	private String areaCode3;		// area_code3
	private String areaName3;		// area_name3
	private String tradingAreaId;		// 商圈id
	private String tradingAreaName;		// 商圈名称
	private String builtUp;		// 建筑面积
	private Integer heatingMode;		// 供暖方式字典
	private String heatingModeName;		// 供暖方式名称
	private Integer houseNum;		// 房屋总数
	private String plotRatio;		// 容积率
	private String greenRate;		// 绿化率
	private String parkingSpace;		// 车位数
	
	
	private String occupyArea;//占地面积
	private String address;//小区地址
	
	private String buildingTypeName;//建筑类型名称
	private String certificateName;//产权年限名称
	private Integer certificate;
	private Integer propertyRight;
	private String propertyRightName;//产权性质名称
	
	public HouseAlikeCommunityTempPo() {
		super();
	}

	public HouseAlikeCommunityTempPo(Long id){
		this();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	public String getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(String builtYear) {
		this.builtYear = builtYear;
	}
	
	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	
	public String getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	
	public String getHouseTypeNum() {
		return houseTypeNum;
	}

	public void setHouseTypeNum(String houseTypeNum) {
		this.houseTypeNum = houseTypeNum;
	}
	
	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}
	
	public String getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(String propertyFee) {
		this.propertyFee = propertyFee;
	}
	
	public String getPropertyCompany() {
		return propertyCompany;
	}

	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}
	
	public String getTotalHouse() {
		return totalHouse;
	}

	public void setTotalHouse(String totalHouse) {
		this.totalHouse = totalHouse;
	}
	
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}
	
	public String getOriginCommunityId() {
		return originCommunityId;
	}

	public void setOriginCommunityId(String originCommunityId) {
		this.originCommunityId = originCommunityId;
	}
	
	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	
	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public String getResblockPosition() {
		return resblockPosition;
	}

	public void setResblockPosition(String resblockPosition) {
		this.resblockPosition = resblockPosition;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public Long getLianjiaId() {
		return lianjiaId;
	}

	public void setLianjiaId(Long lianjiaId) {
		this.lianjiaId = lianjiaId;
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
	
	public String getTradingAreaId() {
		return tradingAreaId;
	}

	public void setTradingAreaId(String tradingAreaId) {
		this.tradingAreaId = tradingAreaId;
	}
	
	public String getTradingAreaName() {
		return tradingAreaName;
	}

	public void setTradingAreaName(String tradingAreaName) {
		this.tradingAreaName = tradingAreaName;
	}
	
	public String getBuiltUp() {
		return builtUp;
	}

	public void setBuiltUp(String builtUp) {
		this.builtUp = builtUp;
	}
	
	public Integer getHeatingMode() {
		return heatingMode;
	}

	public void setHeatingMode(Integer heatingMode) {
		this.heatingMode = heatingMode;
	}
	
	public String getHeatingModeName() {
		return heatingModeName;
	}

	public void setHeatingModeName(String heatingModeName) {
		this.heatingModeName = heatingModeName;
	}
	
	public Integer getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	
	public String getPlotRatio() {
		return plotRatio;
	}

	public void setPlotRatio(String plotRatio) {
		this.plotRatio = plotRatio;
	}
	
	public String getGreenRate() {
		return greenRate;
	}

	public void setGreenRate(String greenRate) {
		this.greenRate = greenRate;
	}
	
	public String getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(String parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	public String getOccupyArea() {
		return occupyArea;
	}

	public void setOccupyArea(String occupyArea) {
		this.occupyArea = occupyArea;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuildingTypeName() {
		return buildingTypeName;
	}

	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public Integer getCertificate() {
		return certificate;
	}

	public void setCertificate(Integer certificate) {
		this.certificate = certificate;
	}

	public Integer getPropertyRight() {
		return propertyRight;
	}

	public void setPropertyRight(Integer propertyRight) {
		this.propertyRight = propertyRight;
	}

	public String getPropertyRightName() {
		return propertyRightName;
	}

	public void setPropertyRightName(String propertyRightName) {
		this.propertyRightName = propertyRightName;
	}
	
}