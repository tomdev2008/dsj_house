package com.dsj.modules.oldhouse.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 17:13:07.
 * @版本: 1.0 .
 */
public class OldHouseRequirePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String requireUseranme;		// 需求人
	private String requirePhone;		// require_phone
	private String tradingArea;		// 商圈
	private String buildingName;		// 楼盘名称
	private String houseType;		// 户型
	private Double budgetMin;		// 最小预算
	private Double budgetMax;		// 最大预算
	private Integer status;		// 状态  1 未处理 2 已处理
	private Date updateTime;		// 更新时间
	private Integer createPerson;		// 创建人id
	private Integer updatePerson;		// 更新人id
	
	public OldHouseRequirePo() {
		super();
	}

	public OldHouseRequirePo(Long id){
		this();
	}
	

	public String getRequireUseranme() {
		return requireUseranme;
	}

	public void setRequireUseranme(String requireUseranme) {
		this.requireUseranme = requireUseranme;
	}
	
	public String getRequirePhone() {
		return requirePhone;
	}

	public void setRequirePhone(String requirePhone) {
		this.requirePhone = requirePhone;
	}
	
	public String getTradingArea() {
		return tradingArea;
	}

	public void setTradingArea(String tradingArea) {
		this.tradingArea = tradingArea;
	}
	
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	
	public Double getBudgetMin() {
		return budgetMin;
	}

	public void setBudgetMin(Double budgetMin) {
		this.budgetMin = budgetMin;
	}
	
	public Double getBudgetMax() {
		return budgetMax;
	}

	public void setBudgetMax(Double budgetMax) {
		this.budgetMax = budgetMax;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}
	
	public Integer getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Integer updatePerson) {
		this.updatePerson = updatePerson;
	}
	
}