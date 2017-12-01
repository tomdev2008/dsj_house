package com.dsj.modules.oldHouseParser.po;

import java.math.BigDecimal;
import java.util.Date;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
public class DicDealLogsCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String originHouseId;		// 源二手房id
	private String originDicId;		// 源小区id
	private String dealNo;		// 成交编号
	private Integer room;		// 室
	private Integer hall;		// 厅
	private Integer floorType;		// floor_type
	private String floorTypeName;		// floor_type_name
	private Double buildArea;		// build_area
	private Date contractDate;		// 合同日期
	private BigDecimal price;		// 总价
	private BigDecimal unitPrice;		// 单价
	private String source;		// source
	private String title;		// 标题
	private String dealInfo;		// 成交信息
	private String renovationName;		// renovation_name
	private Integer renovation;		// 装修情况
	private String orientationsName;		// orientations_name
	private Integer orientations;		// 朝向
	private Integer floorNum;		// 总楼层
	private Integer certificateType;		// 产权满类型 1未满2年 2满两年 3满5年
	private Integer isElevator;		// 是否有电梯
	private String buildYear;		// build_year
	private Integer buildType;		// 建筑类型
	private String buildTypeName;		// 建筑类型名称
	
	private BigDecimal listedPrice;
	
	 
	
	private String imageUrl;		// image_url
	private String originImageUrl;		// 源图片url
	private Integer compayType;		// 公司类型
	
	
	public DicDealLogsCrawlerPo() {
		super();
	}

	public DicDealLogsCrawlerPo(Long id){
		this();
	}
	

	public String getOriginHouseId() {
		return originHouseId;
	}

	public void setOriginHouseId(String originHouseId) {
		this.originHouseId = originHouseId;
	}
	
	public String getOriginDicId() {
		return originDicId;
	}

	public void setOriginDicId(String originDicId) {
		this.originDicId = originDicId;
	}
	
	public String getDealNo() {
		return dealNo;
	}

	public void setDealNo(String dealNo) {
		this.dealNo = dealNo;
	}
	
	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}
	
	public Integer getHall() {
		return hall;
	}

	public void setHall(Integer hall) {
		this.hall = hall;
	}
	
	public Integer getFloorType() {
		return floorType;
	}

	public void setFloorType(Integer floorType) {
		this.floorType = floorType;
	}
	
	public String getFloorTypeName() {
		return floorTypeName;
	}

	public void setFloorTypeName(String floorTypeName) {
		this.floorTypeName = floorTypeName;
	}
	
	public Double getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	
	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDealInfo() {
		return dealInfo;
	}

	public void setDealInfo(String dealInfo) {
		this.dealInfo = dealInfo;
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
	
	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}
	
	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	
	public Integer getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(Integer isElevator) {
		this.isElevator = isElevator;
	}
	
	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	
	public Integer getBuildType() {
		return buildType;
	}

	public void setBuildType(Integer buildType) {
		this.buildType = buildType;
	}
	
	public String getBuildTypeName() {
		return buildTypeName;
	}

	public void setBuildTypeName(String buildTypeName) {
		this.buildTypeName = buildTypeName;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getOriginImageUrl() {
		return originImageUrl;
	}

	public void setOriginImageUrl(String originImageUrl) {
		this.originImageUrl = originImageUrl;
	}
	
	public Integer getCompayType() {
		return compayType;
	}

	public void setCompayType(Integer compayType) {
		this.compayType = compayType;
	}

	public BigDecimal getListedPrice() {
		return listedPrice;
	}

	public void setListedPrice(BigDecimal listedPrice) {
		this.listedPrice = listedPrice;
	}
	
}