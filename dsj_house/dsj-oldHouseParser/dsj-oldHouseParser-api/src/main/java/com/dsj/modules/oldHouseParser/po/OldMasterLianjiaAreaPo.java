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
public class OldMasterLianjiaAreaPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String areaAme;		// 地区名称
	private String areaCode;		// 地区编码
	private String areaListUrl;		// 地区地址
	private Integer houseCount;		// 房源数量
	private Integer status;		// status
	private String tradeName;		// 商圈名称
	private Long tradeId;		// 商圈id
	private String tradeListUrl;		// 商圈地址
	private Long fatherId;
	
	public OldMasterLianjiaAreaPo() {
		super();
	}

	public OldMasterLianjiaAreaPo(Long id){
		this();
	}
	

	public String getAreaAme() {
		return areaAme;
	}

	public void setAreaAme(String areaAme) {
		this.areaAme = areaAme;
	}
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getAreaListUrl() {
		return areaListUrl;
	}

	public void setAreaListUrl(String areaListUrl) {
		this.areaListUrl = areaListUrl;
	}
	
	public Integer getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(Integer houseCount) {
		this.houseCount = houseCount;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	
	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	
	public String getTradeListUrl() {
		return tradeListUrl;
	}

	public void setTradeListUrl(String tradeListUrl) {
		this.tradeListUrl = tradeListUrl;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	
}