package com.dsj.modules.oldHouseParser.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:22.
 * @版本: 1.0 .
 */
public class HouseMasterCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String houseId;		// 编号
	private Long dicId;		// 合并后id
	private String originDicId;		// 原小区ID
	private Integer hall;		// 厅
	private Integer room;		// 室
	private Integer toilet;		// 卫
	private Integer kitchen;		// 厨
	private String houseType;		// 房屋类型字典
	private String houseTypeName;		// 房屋名称类型
	
	private String renovationName;		// 装修情况名称
	private Integer renovation;		// 装修情况字典ID
	private Integer floor;		// 所在楼层
	private Integer floorNum;		// 总楼层
	private Integer floorType;		// 楼层类型 1低 2中 3高
	
	private String floorTypeName;//楼层名称
	
	private String roomNumber1;		// 户室1
	private Integer roomNumber1Cell;		// 户室1单位
	private String roomNumber2;		// 户室2
	private Integer roomNumber2Cell;		// 户室单位
	private String roomNumber;		// 门牌号
	private Double buildArea;		// 建筑面积
	private String buildYear;		// 建造年代
	private String certificateName;		// 产权名称
	private Integer certificate;		// 产权字典id
	private Integer certificateType;		// 产权满类型 1未满2年 2满两年 3满5年
	private Integer certificateTypeName;
	private Integer isKey;		// 1 是 2否是否有钥匙
	private Integer houseOnly;		// 唯一住房
	private Double payments;		// 首付
	private Double price;		// 售价
	private String featuresName;		// 特色名称
	private String features;		// 特色标签 逗号拼接
	private String title;		// 标题,推荐标语
	private String sellingPoint;		// 核心卖点
	private String houseDetail;		// 房源详情
	private String owneMentality;		// 业主心态
	private String villageMatching;		// 小区配套
	private String serviceIntro;		// 服务介绍
	private String orientationsName;		// 朝向名称
	private Integer orientations;		// 朝向字典
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
	private Date updateTime;		// 修改时间
	private String imageUrl;		// 封面
	private Integer companyType;		// 所有中介公司类型串
	private Long fatherId;		// 默认0 父id
	private String originHouseId;		// 爬的数据的房源id
	private Long dicTempId;		// 小区临时表id
	private Integer hasDicStatus;		// 是否找到小区的状态，没有临时表id有值
	private List<OldHousePictureCrawlerPo> oldHousePicture; //图片实体类
	private HouseAlikeCommunityPo houseAlikeCommunity;//小区实体类
	private HouseAgentCrawlerPo agentCrawler;//经纪人实体类
	private List<HousePictureCrawlerPo> pictureCrawler;//小区图片实体类
	private HouseAgentCrawlerPo agent;
	private List<DicDealLogsCrawlerPo> dealList;
	
	private ZydcOldhouseFlagPo woaiwojia;//我爱我家实体类
	private Integer showxs;		// 展示顺序
	
	private String houseTypePicUrl;
	private String houseTypeLjPicUrl;
	
	
	private HouseTradeInfoCrawlerPo tradeInfo;
	
	private Integer isDeal;
	
	private List<MatserHouseTypesPo> matserHouseTypeList;
	
	private String layoutStructure;//户型结构
	private BigDecimal insideArea;//内面积
	private String buildingStruture;//建筑结构
	private String stairScale;//楼梯比例
	private Integer isElevator;//配置电梯
	private String houseTypeInfo;//户型介绍
	private String sfjx;//税费解析
	private String aroundInfo;//周边配套
	private String dicInfo;//小区介绍
	private String subwayDetail;//地铁详情
	private String ring;//几环
	
	private String subwayUrl;
	
	public Integer getShowxs() {
		return showxs;
	}

	public void setShowxs(Integer showxs) {
		this.showxs = showxs;
	}

	public ZydcOldhouseFlagPo getWoaiwojia() {
		return woaiwojia;
	}

	public void setWoaiwojia(ZydcOldhouseFlagPo woaiwojia) {
		this.woaiwojia = woaiwojia;
	}

	public List<HousePictureCrawlerPo> getPictureCrawler() {
		return pictureCrawler;
	}

	public void setPictureCrawler(List<HousePictureCrawlerPo> pictureCrawler) {
		this.pictureCrawler = pictureCrawler;
	}

	public HouseAgentCrawlerPo getAgentCrawler() {
		return agentCrawler;
	}

	public void setAgentCrawler(HouseAgentCrawlerPo agentCrawler) {
		this.agentCrawler = agentCrawler;
	}

	public List<OldHousePictureCrawlerPo> getOldHousePicture() {
		return oldHousePicture;
	}

	public void setOldHousePicture(List<OldHousePictureCrawlerPo> oldHousePicture) {
		this.oldHousePicture = oldHousePicture;
	}

	

	public HouseAlikeCommunityPo getHouseAlikeCommunity() {
		return houseAlikeCommunity;
	}

	public void setHouseAlikeCommunity(HouseAlikeCommunityPo houseAlikeCommunity) {
		this.houseAlikeCommunity = houseAlikeCommunity;
	}

	public HouseMasterCrawlerPo() {
		super();
	}

	public HouseMasterCrawlerPo(Long id){
		this();
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
	
	public String getOriginDicId() {
		return originDicId;
	}

	public void setOriginDicId(String originDicId) {
		this.originDicId = originDicId;
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
	
	public String getRenovationName() {
		return renovationName;
	}

	public void setRenovationName(String renovationName) {
		this.renovationName = renovationName;
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
	
	public Integer getFloorType() {
		return floorType;
	}

	public void setFloorType(Integer floorType) {
		this.floorType = floorType;
	}
	
	public String getRoomNumber1() {
		return roomNumber1;
	}

	public void setRoomNumber1(String roomNumber1) {
		this.roomNumber1 = roomNumber1;
	}
	
	public Integer getRoomNumber1Cell() {
		return roomNumber1Cell;
	}

	public void setRoomNumber1Cell(Integer roomNumber1Cell) {
		this.roomNumber1Cell = roomNumber1Cell;
	}
	
	public String getRoomNumber2() {
		return roomNumber2;
	}

	public void setRoomNumber2(String roomNumber2) {
		this.roomNumber2 = roomNumber2;
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
	
	public Integer getHouseOnly() {
		return houseOnly;
	}

	public void setHouseOnly(Integer houseOnly) {
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
	
	public String getFeaturesName() {
		return featuresName;
	}

	public void setFeaturesName(String featuresName) {
		this.featuresName = featuresName;
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
	
	public String getOrientationsName() {
		return orientationsName;
	}

	public void setOrientationsName(String orientationsName) {
		this.orientationsName = orientationsName;
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
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
	public String getLayoutStructure() {
		return layoutStructure;
	}

	public void setLayoutStructure(String layoutStructure) {
		this.layoutStructure = layoutStructure;
	}

	

	public BigDecimal getInsideArea() {
		return insideArea;
	}

	public void setInsideArea(BigDecimal insideArea) {
		this.insideArea = insideArea;
	}

	public String getBuildingStruture() {
		return buildingStruture;
	}

	public void setBuildingStruture(String buildingStruture) {
		this.buildingStruture = buildingStruture;
	}

	public String getStairScale() {
		return stairScale;
	}

	public void setStairScale(String stairScale) {
		this.stairScale = stairScale;
	}

	public Integer getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(Integer isElevator) {
		this.isElevator = isElevator;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	
	public String getOriginHouseId() {
		return originHouseId;
	}

	public void setOriginHouseId(String originHouseId) {
		this.originHouseId = originHouseId;
	}
	
	public Long getDicTempId() {
		return dicTempId;
	}

	public void setDicTempId(Long dicTempId) {
		this.dicTempId = dicTempId;
	}
	
	public Integer getHasDicStatus() {
		return hasDicStatus;
	}

	public void setHasDicStatus(Integer hasDicStatus) {
		this.hasDicStatus = hasDicStatus;
	}


	public String getFloorTypeName() {
		return floorTypeName;
	}

	public void setFloorTypeName(String floorTypeName) {
		this.floorTypeName = floorTypeName;
	}

	public String getHouseTypeName() {
		return houseTypeName;
	}

	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}
	
	

	public HouseAgentCrawlerPo getAgent() {
		return agent;
	}

	public void setAgent(HouseAgentCrawlerPo agent) {
		this.agent = agent;
	}
	
	

	public Integer getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}
	
	

	public Integer getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(Integer certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	
	

	public String getHouseTypePicUrl() {
		return houseTypePicUrl;
	}

	public void setHouseTypePicUrl(String houseTypePicUrl) {
		this.houseTypePicUrl = houseTypePicUrl;
	}

	public String getHouseTypeLjPicUrl() {
		return houseTypeLjPicUrl;
	}

	public void setHouseTypeLjPicUrl(String houseTypeLjPicUrl) {
		this.houseTypeLjPicUrl = houseTypeLjPicUrl;
	}
	
	
	
	public List<MatserHouseTypesPo> getMatserHouseTypeList() {
		return matserHouseTypeList;
	}

	public void setMatserHouseTypeList(List<MatserHouseTypesPo> matserHouseTypeList) {
		this.matserHouseTypeList = matserHouseTypeList;
	}

	public HouseTradeInfoCrawlerPo getTradeInfo() {
		return tradeInfo;
	}

	public void setTradeInfo(HouseTradeInfoCrawlerPo tradeInfo) {
		this.tradeInfo = tradeInfo;
	}
	
	public String getHouseTypeInfo() {
		return houseTypeInfo;
	}

	public void setHouseTypeInfo(String houseTypeInfo) {
		this.houseTypeInfo = houseTypeInfo;
	}

	
	public String getSfjx() {
		return sfjx;
	}

	public void setSfjx(String sfjx) {
		this.sfjx = sfjx;
	}

	public String getAroundInfo() {
		return aroundInfo;
	}

	public void setAroundInfo(String aroundInfo) {
		this.aroundInfo = aroundInfo;
	}

	public String getDicInfo() {
		return dicInfo;
	}

	public void setDicInfo(String dicInfo) {
		this.dicInfo = dicInfo;
	}
	
	public List<DicDealLogsCrawlerPo> getDealList() {
		return dealList;
	}

	public void setDealList(List<DicDealLogsCrawlerPo> dealList) {
		this.dealList = dealList;
	}

	

	public String getSubwayDetail() {
		return subwayDetail;
	}

	public void setSubwayDetail(String subwayDetail) {
		this.subwayDetail = subwayDetail;
	}

	public String getRing() {
		return ring;
	}

	public void setRing(String ring) {
		this.ring = ring;
	}
	
	

	public String getSubwayUrl() {
		return subwayUrl;
	}

	public void setSubwayUrl(String subwayUrl) {
		this.subwayUrl = subwayUrl;
	}

	@Override
	public String toString() {
		return "HouseMasterCrawlerPo [houseId=" + houseId + ", dicId=" + dicId + ", originDicId=" + originDicId
				+ ", hall=" + hall + ", room=" + room + ", toilet=" + toilet + ", kitchen=" + kitchen + ", houseType="
				+ houseType + ", renovationName=" + renovationName + ", renovation=" + renovation + ", floor=" + floor
				+ ", floorNum=" + floorNum + ", floorType=" + floorType + ", roomNumber1=" + roomNumber1
				+ ", roomNumber1Cell=" + roomNumber1Cell + ", roomNumber2=" + roomNumber2 + ", roomNumber2Cell="
				+ roomNumber2Cell + ", roomNumber=" + roomNumber + ", buildArea=" + buildArea + ", buildYear="
				+ buildYear + ", certificateName=" + certificateName + ", certificate=" + certificate
				+ ", certificateType=" + certificateType + ", isKey=" + isKey + ", houseOnly=" + houseOnly
				+ ", payments=" + payments + ", price=" + price + ", featuresName=" + featuresName + ", features="
				+ features + ", title=" + title + ", sellingPoint=" + sellingPoint + ", houseDetail=" + houseDetail
				+ ", owneMentality=" + owneMentality + ", villageMatching=" + villageMatching + ", serviceIntro="
				+ serviceIntro + ", orientationsName=" + orientationsName + ", orientations=" + orientations
				+ ", houseYear=" + houseYear + ", structure=" + structure + ", liftRatio=" + liftRatio
				+ ", heatingMode=" + heatingMode + ", lift=" + lift + ", listingDate=" + listingDate + ", dealDate="
				+ dealDate + ", property=" + property + ", mortgage=" + mortgage + ", roomBook=" + roomBook
				+ ", deleteFlag=" + deleteFlag + ", unitPrice=" + unitPrice + ", monthpay=" + monthpay + ", updateTime="
				+ updateTime + ", imageUrl=" + imageUrl + ", companyType=" + companyType + ", fatherId=" + fatherId
				+ ", originHouseId=" + originHouseId + ", dicTempId=" + dicTempId + ", hasDicStatus=" + hasDicStatus
				+ "]";
	}

	
}