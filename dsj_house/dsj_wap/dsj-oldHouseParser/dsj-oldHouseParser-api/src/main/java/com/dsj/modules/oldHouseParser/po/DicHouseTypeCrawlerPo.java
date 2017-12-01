package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
public class DicHouseTypeCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer room;		// 室
	private Integer hall;		// 厅
	private Integer toilet;		// 卫
	private Integer kitchen;		// 厨
	private String orientationsName;		// 朝向名称
	private String useRate;		// 使用率
	private String originHouseTypeId;		// 源户型id
	private String originDicId;		// 源小区id
	private String imageUrl;		// image_url
	private String originImageUrl;		// origin_image_url
	
	private BigDecimal buildArea;//建筑面积
	
	private String price;
	
	private List<DicDealLogsCrawlerPo> dealList;
	
	public DicHouseTypeCrawlerPo() {
		super();
	}

	public DicHouseTypeCrawlerPo(Long id){
		this();
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
	
	public String getOrientationsName() {
		return orientationsName;
	}

	public void setOrientationsName(String orientationsName) {
		this.orientationsName = orientationsName;
	}
	
	public String getUseRate() {
		return useRate;
	}

	public void setUseRate(String useRate) {
		this.useRate = useRate;
	}
	
	public String getOriginHouseTypeId() {
		return originHouseTypeId;
	}

	public void setOriginHouseTypeId(String originHouseTypeId) {
		this.originHouseTypeId = originHouseTypeId;
	}
	
	public String getOriginDicId() {
		return originDicId;
	}

	public void setOriginDicId(String originDicId) {
		this.originDicId = originDicId;
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

	public BigDecimal getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(BigDecimal buildArea) {
		this.buildArea = buildArea;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<DicDealLogsCrawlerPo> getDealList() {
		return dealList;
	}

	public void setDealList(List<DicDealLogsCrawlerPo> dealList) {
		this.dealList = dealList;
	}
	
	
}