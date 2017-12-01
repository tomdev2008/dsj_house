package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.楼盘字典
 * @作者: kk.
 * @创建时间: 2017-07-16 08:34:03.
 * @版本: 1.0 .
 */
public class HouseDirectoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long newId;		// 新盘id
	private String sprayName;		// 喷涂楼盘名称
	private String cardName;		// 证载楼盘名称
	private String dicAlias;		// 楼盘别名
	private String extend1;		// 推广名称1
	private String extend2;		// 推广名称2
	private String spellName;		// 楼盘拼写
	private String spellHead;		// 楼盘拼写简写
	private String oldName;		// 楼盘曾用名
	private String developers;		// 开发商
	private String phone;		// 400电话
	private String address;		// 楼盘地址
	private String saleAddress;		// 售楼处地址
	private String describe;		// 楼盘简介
	private String openDate;		// 开盘时间
	private String handDate;		// 交房时间
	private String dicTrait;		// 楼盘特点
	private Integer achYear;		// 建筑年代
	private Integer certificate;		// 产权年限
	private Integer propertyRight;		// 产权性质
	private String wyType;		// 物业类型
	private String achType;		// 建筑类型
	private Integer landYear;		// 土地使用年限
	private String parkingSpace;		// 车位比
	private String prep;		// 预售许可证
	private String parkNumber;		// 车位数
	private Integer houseNum;		// 房屋总数
	private Integer floorNum;		// 楼栋总数
	private String schedule;		// 工程进度
	private String loopLine;		// 所在环线
	private String dimension;		// 楼盘维度
	private String accuracy;		// 楼盘精度
	private String property;		// 物业公司
	private String occupyArea;		// 占地面积
	private String builtUp;		// 总建筑面积
	private String plotRatio;		// 容积率
	private Double greenRate;		// 绿化率
	private Integer averagePrice;		// 小区均价
	private Long heatingMode;		// 供暖方式
	private String propertyFee;		// 物业费
	private String description;		// 描述
	private String landUsage;		// 土地使用年限
	private String areaCode1;		// 地区邮编 省
	private String areaName1;		// 地区名1
	private String areaCode2;		// 市
	private String areaName2;		// 地区名2
	private String areaCode3;		// 区
	private String areaName3;		// 地区名3
	private Long tradingAreaId;		// 商圈ID
	private String postcode;		// 地区邮编
	private String tradingAreaName;		// 商圈名称
	private Integer createPreson;		// 创建人
	private Integer updatePreson;		// 修改人
	private Date updateTime;		// 修改时间
	private Integer deleteFlag;		// 删除标记
	private String propertyRightName;		// property_right_name
	private Long status;		// 状态
	private String pageUrl;		// 抓取页面
	
	private String originCommunityId;//源小区id
	public HouseDirectoryPo() {
		super();
	}

	public HouseDirectoryPo(Long id){
		this();
	}
	

	public Long getNewId() {
		return newId;
	}

	public void setNewId(Long newId) {
		this.newId = newId;
	}
	
	public String getSprayName() {
		return sprayName;
	}

	public void setSprayName(String sprayName) {
		this.sprayName = sprayName;
	}
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	public String getDicAlias() {
		return dicAlias;
	}

	public void setDicAlias(String dicAlias) {
		this.dicAlias = dicAlias;
	}
	
	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
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
	
	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	
	public String getHandDate() {
		return handDate;
	}

	public void setHandDate(String handDate) {
		this.handDate = handDate;
	}
	
	public String getDicTrait() {
		return dicTrait;
	}

	public void setDicTrait(String dicTrait) {
		this.dicTrait = dicTrait;
	}
	
	public Integer getAchYear() {
		return achYear;
	}

	public void setAchYear(Integer achYear) {
		this.achYear = achYear;
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
	
	public String getWyType() {
		return wyType;
	}

	public void setWyType(String wyType) {
		this.wyType = wyType;
	}
	
	public String getAchType() {
		return achType;
	}

	public void setAchType(String achType) {
		this.achType = achType;
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
	
	public String getPrep() {
		return prep;
	}

	public void setPrep(String prep) {
		this.prep = prep;
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
	
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public String getLoopLine() {
		return loopLine;
	}

	public void setLoopLine(String loopLine) {
		this.loopLine = loopLine;
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
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
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
	
	public Integer getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Integer averagePrice) {
		this.averagePrice = averagePrice;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLandUsage() {
		return landUsage;
	}

	public void setLandUsage(String landUsage) {
		this.landUsage = landUsage;
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
	
	public Integer getCreatePreson() {
		return createPreson;
	}

	public void setCreatePreson(Integer createPreson) {
		this.createPreson = createPreson;
	}
	
	public Integer getUpdatePreson() {
		return updatePreson;
	}

	public void setUpdatePreson(Integer updatePreson) {
		this.updatePreson = updatePreson;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public String getPropertyRightName() {
		return propertyRightName;
	}

	public void setPropertyRightName(String propertyRightName) {
		this.propertyRightName = propertyRightName;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getSpellHead() {
		return spellHead;
	}

	public void setSpellHead(String spellHead) {
		this.spellHead = spellHead;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getOriginCommunityId() {
		return originCommunityId;
	}

	public void setOriginCommunityId(String originCommunityId) {
		this.originCommunityId = originCommunityId;
	}
	
}