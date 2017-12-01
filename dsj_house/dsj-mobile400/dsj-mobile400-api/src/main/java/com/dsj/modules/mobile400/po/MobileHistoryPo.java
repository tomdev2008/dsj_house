package com.dsj.modules.mobile400.po;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-18 12:49:04.
 * @版本: 1.0 .
 */
public class MobileHistoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long mobileId;		// 号码id
	private String mobile;
	private Integer status;		// 1绑定 2未绑定
	private Long houseId;		// house_id
	private String houseName;		// 楼盘名称
	private Long agentId;		// agent_id
	private String agentName;		// 经纪人姓名
	private Integer propertyId;		//权证代办人id
	private String propertyName;	//权证代办人名称
	
	
	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public MobileHistoryPo() {
		super();
	}

	public MobileHistoryPo(Long id){
		this();
	}
	

	public Long getMobileId() {
		return mobileId;
	}

	public void setMobileId(Long mobileId) {
		this.mobileId = mobileId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
}