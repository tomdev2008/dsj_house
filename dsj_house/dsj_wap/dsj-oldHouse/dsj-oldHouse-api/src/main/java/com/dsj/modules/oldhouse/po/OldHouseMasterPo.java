package com.dsj.modules.oldhouse.po;

import java.util.Date;

import com.dsj.common.entity.BaseEntity;
import com.dsj.common.enums.FloorTypeEnum;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-28 14:44:14.
 * @版本: 1.0 .
 */
public class OldHouseMasterPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String houseId;		// 系统生成房源ID
	private Long dicId;		// 楼盘ID
	private String recordNo;		// 备案编号
	private Integer hall;		// 厅
	private Integer room;		// 室
	private Integer toilet;		// 卫
	private Integer kitchen;		// 厨
	private String houseType;		// 房屋类型
	private Integer renovation;		// 装修情况
	private Integer floor;		// 所在楼层
	private Integer floorNum;		// 总楼层
	private Integer floorType;
	private String roomNumber1;		// 户室1
	private Integer roomNumber1Cell;		// 户室1单位
	private String roomNumber2;		// 户室2
	private Integer roomNumber2Cell;		// 户室单位
	private String roomNumber;		// 门牌号
	private Double buildArea;		// 建筑面积
	private String buildYear;		// 建造年代
	private Integer certificate;		// 产权
	private Integer certificateType;		// 满二年，满五年，未满二年
	private Integer isKey;//是否有钥匙
	private Integer certificateTwo;		// 是否产权证满2
	private Integer certificateFive;		// 是否产权证满5
	private String houseOnly;		// 唯一住房
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
	private Integer structure;		// 建筑结构
	private Integer liftRatio;		// 梯户比例
	private String heatingMode;		// 供暖方式
	private String lift;		// 配备电梯
	private Date listingDate;		// 挂牌时间
	private Date dealDate;		// 上次交易时间
	private String property;		// 产权所有
	private String mortgage;		// 抵押信息
	private String roomBook;		// 房本备件
	private Integer deleteFlag;		// 删除标记
	private Double unitPrice;		// 单价
	private Double monthpay;		// 月供
	private Integer createPerson;		// 创建人
	private Integer updatePreson;		// 修改人
	private Date updateTime;		// 修改时间
	private Integer status;		// 1 未上架 2上架 3下架
	private String imageUrl;

	private Integer companyType;
	private String companyTypes;
	private String ownerName;//业主姓名
	private String ownerPhone;//业主电话
	private Long fatherId;
	
	private Integer isCrawlerAdd;//是否是爬虫新增的数据
	private String originHouseId;
	
	private Long userId;
	private String[] featureArray;
	
	private String featureNames;
	
	//转化
	private String floorTypeName;
	public OldHouseMasterPo() {
		super();
	}

	public OldHouseMasterPo(Long id){
		this();
	}
	
	public String[] getFeatureArray() {
		if(this.features!=null){
			featureArray = this.features.split(",");
		}
		return featureArray;
	}

	public void setFeatureArray(String[] featureArray) {
		this.featureArray = featureArray;
	}
	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Long getDicId() {
		return dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}
	
	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
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
	
	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	
	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}
	
	
	public String getRoomNumber1() {
		return roomNumber1;
	}

	public void setRoomNumber1(String roomNumber1) {
		this.roomNumber1 = roomNumber1;
	}

	public String getRoomNumber2() {
		return roomNumber2;
	}

	public void setRoomNumber2(String roomNumber2) {
		this.roomNumber2 = roomNumber2;
	}

	public Integer getRoomNumber1Cell() {
		return roomNumber1Cell;
	}

	public void setRoomNumber1Cell(Integer roomNumber1Cell) {
		this.roomNumber1Cell = roomNumber1Cell;
	}
	
	
	
	public Integer getRoomNumber2Cell() {
		return roomNumber2Cell;
	}

	public void setRoomNumber2Cell(Integer roomNumber2Cell) {
		this.roomNumber2Cell = roomNumber2Cell;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
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
	
	public Integer getCertificate() {
		return certificate;
	}

	public void setCertificate(Integer certificate) {
		this.certificate = certificate;
	}
	
	public Integer getCertificateTwo() {
		return certificateTwo;
	}

	public void setCertificateTwo(Integer certificateTwo) {
		this.certificateTwo = certificateTwo;
	}
	
	public Integer getCertificateFive() {
		return certificateFive;
	}

	public void setCertificateFive(Integer certificateFive) {
		this.certificateFive = certificateFive;
	}
	
	public String getHouseOnly() {
		return houseOnly;
	}

	public void setHouseOnly(String houseOnly) {
		this.houseOnly = houseOnly;
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
	
	public Integer getStructure() {
		return structure;
	}

	public void setStructure(Integer structure) {
		this.structure = structure;
	}
	
	public Integer getLiftRatio() {
		return liftRatio;
	}

	public void setLiftRatio(Integer liftRatio) {
		this.liftRatio = liftRatio;
	}
	
	public String getHeatingMode() {
		return heatingMode;
	}

	public void setHeatingMode(String heatingMode) {
		this.heatingMode = heatingMode;
	}
	
	public String getLift() {
		return lift;
	}

	public void setLift(String lift) {
		this.lift = lift;
	}
	
	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}
	
	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getMortgage() {
		return mortgage;
	}

	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}
	
	public String getRoomBook() {
		return roomBook;
	}

	public void setRoomBook(String roomBook) {
		this.roomBook = roomBook;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getFloorType() {
		return floorType;
	}

	public void setFloorType(Integer floorType) {
		this.floorType = floorType;
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public Integer getIsKey() {
		return isKey;
	}

	public void setIsKey(Integer isKey) {
		this.isKey = isKey;
	}

	public String getFeatureNames() {
		return featureNames;
	}

	public void setFeatureNames(String featureNames) {
		this.featureNames = featureNames;
	}

	public Integer getIsCrawlerAdd() {
		return isCrawlerAdd;
	}

	public void setIsCrawlerAdd(Integer isCrawlerAdd) {
		this.isCrawlerAdd = isCrawlerAdd;
	}

	public String getOriginHouseId() {
		return originHouseId;
	}

	public void setOriginHouseId(String originHouseId) {
		this.originHouseId = originHouseId;
	}
	
	
}