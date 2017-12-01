package com.dsj.modules.solr.vo.newHouse;

import java.io.Serializable;

public class NewHouseQueryVo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String areaCode2;//地区编码2
	private String areaCode3;//地区编码3
	private Long tradingAreaId;//'商圈ID'
	private Integer price;		// 售价
	private Integer room;
	private Integer pageNum;
	private Integer wyType;
	private String dictrait;
	private Integer buildArea;
	private String keywords;//小区名
	private String areaCode3s;//地区编码3
	private String tradingAreaIds;//
	private String dicIds;
	private String  subwayline;
	private String subway;
	private Integer companyType;//来源类型
	private Integer isSaleOut;
	
	private Integer pmn;//价格最小
	private Integer pmx;//价格最大
	private Integer amn;//面积最小
	private Integer amx;//面积最大
	
	private Integer ordering = 1;
	
	private String kw;//全匹配
	

	public Integer getIsSaleOut() {
		return isSaleOut;
	}

	public void setIsSaleOut(Integer isSaleOut) {
		this.isSaleOut = isSaleOut;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public Integer getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(Integer buildArea) {
		this.buildArea = buildArea;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public String getAreaCode3() {
		return areaCode3;
	}

	public void setAreaCode3(String areaCode3) {
		this.areaCode3 = areaCode3;
	}

	public Long getTradingAreaId() {
		return tradingAreaId;
	}

	public void setTradingAreaId(Long tradingAreaId) {
		this.tradingAreaId = tradingAreaId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getWyType() {
		return wyType;
	}

	public void setWyType(Integer wyType) {
		this.wyType = wyType;
	}

	public String getDictrait() {
		return dictrait;
	}

	public void setDictrait(String dictrait) {
		this.dictrait = dictrait;
	}

	public String getAreaCode3s() {
		return areaCode3s;
	}

	public void setAreaCode3s(String areaCode3s) {
		this.areaCode3s = areaCode3s;
	}

	public String getTradingAreaIds() {
		return tradingAreaIds;
	}

	public void setTradingAreaIds(String tradingAreaIds) {
		this.tradingAreaIds = tradingAreaIds;
	}

	public String getDicIds() {
		return dicIds;
	}

	public void setDicIds(String dicIds) {
		this.dicIds = dicIds;
	}

	public Integer getPmn() {
		return pmn;
	}

	public void setPmn(Integer pmn) {
		this.pmn = pmn;
	}

	public Integer getPmx() {
		return pmx;
	}

	public void setPmx(Integer pmx) {
		this.pmx = pmx;
	}

	public Integer getAmn() {
		return amn;
	}

	public void setAmn(Integer amn) {
		this.amn = amn;
	}

	public Integer getAmx() {
		return amx;
	}

	public void setAmx(Integer amx) {
		this.amx = amx;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}


	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSubwayline() {
		return subwayline;
	}

	public void setSubwayline(String subwayline) {
		this.subwayline = subwayline;
	}

	public String getSubway() {
		return subway;
	}

	public void setSubway(String subway) {
		this.subway = subway;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	
	
	
	
	
	
}
