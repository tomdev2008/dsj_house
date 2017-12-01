package com.dsj.modules.newhouse.po;

import com.dsj.common.entity.BaseEntity;
import com.dsj.common.utils.DateUtils;
import com.dsj.modules.newhouse.enums.NewHouseSaleStatusEnum;

import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 18:38:48.
 * @版本: 1.0 .
 */
public class NewHouseDirectoryAuthPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer edit;		// 是否展示的状态
	private String code;		// 自动生成的编码
	private String name;		// 楼盘名称
	private String houseTypeName;		// 户型名称
	private String dicTrait;		// 楼盘特点(逗号分隔)
	private String dicTraitName;		// 楼盘特点名称
	private String wyType;		// 物业类型
	private String wyTypeName;		// 物业类型名称
	private String aroundMinPrice;		// 周边最低单价
	private String aroundMaxPrice;		// 周边最高单价
	private String developers;		// 开发商
	private String address;		// 楼盘地址
	private String accuracy;		// 经度
	private String dimension;		// 维度
	private Long areaLevalOne;		// 省
	private Long areaLevalTwo;		// 市
	private Long areaLevalThree;		// 区
	private Long tradingArea;		// 商圈ID
	private Long lineNum;		// 环线(字典表)
	private String areaLevalOneName;		// 省
	private String areaLevalTwoName;		// 市
	private String areaLevalThreeName;		// 区
	private String tradingAreaName;		// 商圈ID
	private String lineNumName;		// 环线(字典表)
	private String phone;		// 售楼处电话
	private String discount;		// 楼盘优惠
	private String openDate;		// 抓取的开盘时间
	private String handDate;		// 交房时间
	private String saleAddress;		// 售楼处地址
	private String prep;		// 预售许可证(逗号分隔)
	private Long status;		// 销售状态
	private String achType;		// 建筑类型
	private String achTypeName;		// 建筑类型名称
	private String greenRate;		// 绿化率
	private String houseCount;		// 规划户数
	private String floorMsg;		// 楼层状况
	private String banMsg;		// 栋座信息
	private String schedule;		// 工程进度
	private String property;		// 物业公司
	private String parkNumber;		// 车位数
	private String parkingSpace;		// 车位比
	private Integer isTure;		// 是否上架(包含已删除)
	private Integer authStatus;		// 审核状态
	private String content;		// 审批意见
	private Date commitTime;		// 提交时间
	private Date authTime;		// 提交时间
	private Long commitPerson;		// 提交人
	private Long authPerson;		// 审批人
	private Long maintainPerson;		// 维护人(经纪人id)
	private Long evelopersPerson;    //开发商ID
	private Long createPreson;		// 创建人
	private Long updatePreson;		// 修改人
	private Date updateTime;		// 修改时间
	private Long oldId;		// 扒取数据的id
	private Integer dataType;
	private String sprayName;		// 喷涂楼盘名称
	private String cardName;		// 证载楼盘名称
	private String dicAlias;		// 楼盘别名
	private Integer achYear;		// ach_year
	private Integer landYear;		// land_year
	private String occupyArea;		// 占地面积
	private String builtUp;		// 总建筑面积
	private String address1;		// address1
	private String address2;		// address2
	private String address3;		// address3
	private String address4;		// address4
	private String zipCode;		// 地区邮编
	private Long certificate;		// 产权性质
	private String certificateName;		// 产权性质名称
	private String describe;		// 楼盘简介
	private String spellName;		// 楼盘拼写
	private String oldName;		// 楼盘曾用名
	private int isBind;		// 是否绑定楼盘 0：否  1：是
	
	private String coordinate;//坐标拼接值
	
	private String statusName;
	
	private String updateTimeName;//更新时间
	
	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Date getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public NewHouseDirectoryAuthPo() {
		super();
	}

	public NewHouseDirectoryAuthPo(Long id){
		this();
	}
	
	public Integer getEdit() {
		return edit;
	}

	public void setEdit(Integer edit) {
		this.edit = edit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHouseTypeName() {
		return houseTypeName;
	}

	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}
	
	public String getDicTrait() {
		return dicTrait;
	}

	public void setDicTrait(String dicTrait) {
		this.dicTrait = dicTrait;
	}
	
	public String getDicTraitName() {
		return dicTraitName;
	}

	public void setDicTraitName(String dicTraitName) {
		this.dicTraitName = dicTraitName;
	}
	
	public String getWyType() {
		return wyType;
	}

	public void setWyType(String wyType) {
		this.wyType = wyType;
	}
	
	public String getWyTypeName() {
		return wyTypeName;
	}

	public void setWyTypeName(String wyTypeName) {
		this.wyTypeName = wyTypeName;
	}
	
	public String getAroundMinPrice() {
		return aroundMinPrice;
	}

	public void setAroundMinPrice(String aroundMinPrice) {
		this.aroundMinPrice = aroundMinPrice;
	}
	
	public String getAroundMaxPrice() {
		return aroundMaxPrice;
	}

	public void setAroundMaxPrice(String aroundMaxPrice) {
		this.aroundMaxPrice = aroundMaxPrice;
	}
	
	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	public Long getAreaLevalOne() {
		return areaLevalOne;
	}

	public void setAreaLevalOne(Long areaLevalOne) {
		this.areaLevalOne = areaLevalOne;
	}
	
	public Long getAreaLevalTwo() {
		return areaLevalTwo;
	}

	public void setAreaLevalTwo(Long areaLevalTwo) {
		this.areaLevalTwo = areaLevalTwo;
	}
	
	public Long getAreaLevalThree() {
		return areaLevalThree;
	}

	public void setAreaLevalThree(Long areaLevalThree) {
		this.areaLevalThree = areaLevalThree;
	}
	
	public Long getTradingArea() {
		return tradingArea;
	}

	public void setTradingArea(Long tradingArea) {
		this.tradingArea = tradingArea;
	}
	
	public Long getLineNum() {
		return lineNum;
	}

	public void setLineNum(Long lineNum) {
		this.lineNum = lineNum;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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
	
	public String getSaleAddress() {
		return saleAddress;
	}

	public void setSaleAddress(String saleAddress) {
		this.saleAddress = saleAddress;
	}
	
	public String getPrep() {
		return prep;
	}

	public void setPrep(String prep) {
		this.prep = prep;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public String getAchType() {
		return achType;
	}

	public void setAchType(String achType) {
		this.achType = achType;
	}
	
	public String getAchTypeName() {
		return achTypeName;
	}

	public void setAchTypeName(String achTypeName) {
		this.achTypeName = achTypeName;
	}
	
	public String getGreenRate() {
		return greenRate;
	}

	public void setGreenRate(String greenRate) {
		this.greenRate = greenRate;
	}
	
	public String getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(String houseCount) {
		this.houseCount = houseCount;
	}

	public String getFloorMsg() {
		return floorMsg;
	}

	public void setFloorMsg(String floorMsg) {
		this.floorMsg = floorMsg;
	}
	
	public String getBanMsg() {
		return banMsg;
	}

	public void setBanMsg(String banMsg) {
		this.banMsg = banMsg;
	}
	
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getParkNumber() {
		return parkNumber;
	}

	public void setParkNumber(String parkNumber) {
		this.parkNumber = parkNumber;
	}
	
	public String getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(String parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	
	public Integer getIsTure() {
		return isTure;
	}

	public void setIsTure(Integer isTure) {
		this.isTure = isTure;
	}
	
	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	
	public Long getCommitPerson() {
		return commitPerson;
	}

	public void setCommitPerson(Long commitPerson) {
		this.commitPerson = commitPerson;
	}
	
	public Long getAuthPerson() {
		return authPerson;
	}

	public void setAuthPerson(Long authPerson) {
		this.authPerson = authPerson;
	}
	
	public Long getMaintainPerson() {
		return maintainPerson;
	}

	public void setMaintainPerson(Long maintainPerson) {
		this.maintainPerson = maintainPerson;
	}
	
	public Long getCreatePreson() {
		return createPreson;
	}

	public void setCreatePreson(Long createPreson) {
		this.createPreson = createPreson;
	}
	
	public Long getUpdatePreson() {
		return updatePreson;
	}

	public void setUpdatePreson(Long updatePreson) {
		this.updatePreson = updatePreson;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Long getOldId() {
		return oldId;
	}

	public void setOldId(Long oldId) {
		this.oldId = oldId;
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
	
	public Integer getAchYear() {
		return achYear;
	}

	public void setAchYear(Integer achYear) {
		this.achYear = achYear;
	}
	
	public Integer getLandYear() {
		return landYear;
	}

	public void setLandYear(Integer landYear) {
		this.landYear = landYear;
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
	
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public Long getCertificate() {
		return certificate;
	}

	public void setCertificate(Long certificate) {
		this.certificate = certificate;
	}
	
	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public Long getEvelopersPerson() {
		return evelopersPerson;
	}

	public void setEvelopersPerson(Long evelopersPerson) {
		this.evelopersPerson = evelopersPerson;
	}

	public String getAreaLevalOneName() {
		return areaLevalOneName;
	}

	public void setAreaLevalOneName(String areaLevalOneName) {
		this.areaLevalOneName = areaLevalOneName;
	}

	public String getAreaLevalTwoName() {
		return areaLevalTwoName;
	}

	public void setAreaLevalTwoName(String areaLevalTwoName) {
		this.areaLevalTwoName = areaLevalTwoName;
	}

	public String getAreaLevalThreeName() {
		return areaLevalThreeName;
	}

	public void setAreaLevalThreeName(String areaLevalThreeName) {
		this.areaLevalThreeName = areaLevalThreeName;
	}

	public String getTradingAreaName() {
		return tradingAreaName;
	}

	public void setTradingAreaName(String tradingAreaName) {
		this.tradingAreaName = tradingAreaName;
	}

	public String getLineNumName() {
		return lineNumName;
	}

	public void setLineNumName(String lineNumName) {
		this.lineNumName = lineNumName;
	}

	public int getIsBind() {
		return isBind;
	}

	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}

	public String getStatusName() {
		if(status!=null){
			return NewHouseSaleStatusEnum.getEnum(status.intValue()).getDesc();
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUpdateTimeName() {
		
		return updateTimeName;
	}

	public void setUpdateTimeName(String updateTimeName) {
		this.updateTimeName = updateTimeName;
	}
	
}