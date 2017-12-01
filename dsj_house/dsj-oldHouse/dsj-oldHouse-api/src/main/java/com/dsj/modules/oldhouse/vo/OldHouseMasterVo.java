package com.dsj.modules.oldhouse.vo;

import java.util.Date;

import com.dsj.modules.oldhouse.enums.HouseStatusEnum;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;

public class OldHouseMasterVo extends OldHouseMasterPo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String realname;
	private String tradingAreaName;
	private String areaName3;
	private String statusName;
	private String sprayName;
	private String companyName;
	private String lookTime;
	private Integer objectId;//我的关注 表的 id
	private Integer isRecomend;
	private Long loupanShowId;
	private String featureName;
	private String[] featureArrayName;
	public String[] getFeatureArrayName() {
		if(this.featureName!=null){
			featureArrayName = this.featureName.split(",");
		}
		return featureArrayName;
	}

	public void setFeatureArrayName(String[] featureArrayName) {
		this.featureArrayName = featureArrayName;
	}
	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getLookTime() {
		return lookTime;
	}

	public void setLookTime(String lookTime) {
		this.lookTime = lookTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getTradingAreaName() {
		return tradingAreaName;
	}

	public void setTradingAreaName(String tradingAreaName) {
		this.tradingAreaName = tradingAreaName;
	}

	public String getAreaName3() {
		return areaName3;
	}

	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}

	public String getStatusName() {
		if(getStatus()!=null){
			statusName= HouseStatusEnum.getEnum(getStatus()).getDesc();
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSprayName() {
		return sprayName;
	}

	public void setSprayName(String sprayName) {
		this.sprayName = sprayName;
	}

	public Integer getIsRecomend() {
		return isRecomend;
	}

	public void setIsRecomend(Integer isRecomend) {
		this.isRecomend = isRecomend;
	}

	public Long getLoupanShowId() {
		return loupanShowId;
	}

	public void setLoupanShowId(Long loupanShowId) {
		this.loupanShowId = loupanShowId;
	}

	
}
