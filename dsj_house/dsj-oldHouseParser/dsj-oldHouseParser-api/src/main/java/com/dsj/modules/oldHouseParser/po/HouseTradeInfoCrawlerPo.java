package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
public class HouseTradeInfoCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String originHouseId;		// origin_house_id
	private Date listingDate;		// 挂牌时间
	private String tradeOwnership;		// 交易权属
	private String housingUse;		// 用途
	private Date lastPayDate;		// 上次交易时间
	private String certificateTypeName;		// 房屋年限
	private String propertyRightOwnership;		//产权所属
	private String mortgageInfo;		// 抵押信息
	private String spareParts;		// 房本备份
	
	public HouseTradeInfoCrawlerPo() {
		super();
	}

	public HouseTradeInfoCrawlerPo(Long id){
		this();
	}
	

	public String getOriginHouseId() {
		return originHouseId;
	}

	public void setOriginHouseId(String originHouseId) {
		this.originHouseId = originHouseId;
	}
	
	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}
	
	public String getTradeOwnership() {
		return tradeOwnership;
	}

	public void setTradeOwnership(String tradeOwnership) {
		this.tradeOwnership = tradeOwnership;
	}
	
	public String getHousingUse() {
		return housingUse;
	}

	public void setHousingUse(String housingUse) {
		this.housingUse = housingUse;
	}
	
	public Date getLastPayDate() {
		return lastPayDate;
	}

	public void setLastPayDate(Date lastPayDate) {
		this.lastPayDate = lastPayDate;
	}
	
	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	
	public String getPropertyRightOwnership() {
		return propertyRightOwnership;
	}

	public void setPropertyRightOwnership(String propertyRightOwnership) {
		this.propertyRightOwnership = propertyRightOwnership;
	}
	
	public String getMortgageInfo() {
		return mortgageInfo;
	}

	public void setMortgageInfo(String mortgageInfo) {
		this.mortgageInfo = mortgageInfo;
	}
	
	public String getSpareParts() {
		return spareParts;
	}

	public void setSpareParts(String spareParts) {
		this.spareParts = spareParts;
	}
	
}