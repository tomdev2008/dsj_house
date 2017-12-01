package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:46.
 * @版本: 1.0 .
 */
public class HouseTypeDealCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String originHouseId;		// 源房源id
	private String originDicId;		// 源小区id
	private String originHouseTypeId;		// 源户型id
	
	private Date contractDate;		// 合同日期
	private Long houseTypeId;
	private Long dealId;
	
	public HouseTypeDealCrawlerPo() {
		super();
	}

	public HouseTypeDealCrawlerPo(Long id){
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
	
	public String getOriginHouseTypeId() {
		return originHouseTypeId;
	}

	public void setOriginHouseTypeId(String originHouseTypeId) {
		this.originHouseTypeId = originHouseTypeId;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Long getHouseTypeId() {
		return houseTypeId;
	}

	public void setHouseTypeId(Long houseTypeId) {
		this.houseTypeId = houseTypeId;
	}

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	
}