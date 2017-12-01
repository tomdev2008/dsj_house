package com.dsj.modules.rent.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
public class RentHouseEntrustPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String entrustUsername;		// 委托人
	private String entrustPhone;		// entrust_phone
	private String buildingName;		// 小区名称
	private String buildingNum;		// 楼栋号
	private String unitNum;		// 单元号
	private String roomNum;		// 门牌号
	private Double expectedPrice;		// 期望出租价
	private Integer status;		// 状态  1 未处理 2 已处理
	private Date updateTime;		// 更新时间
	private Integer createPerson;		// 创建人id
	private Integer updatePerson;		// 更新人id
	private String city;//所在城市
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public RentHouseEntrustPo() {
		super();
	}

	public RentHouseEntrustPo(Long id){
		this();
	}
	

	public String getEntrustUsername() {
		return entrustUsername;
	}

	public void setEntrustUsername(String entrustUsername) {
		this.entrustUsername = entrustUsername;
	}
	
	public String getEntrustPhone() {
		return entrustPhone;
	}

	public void setEntrustPhone(String entrustPhone) {
		this.entrustPhone = entrustPhone;
	}
	
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public String getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	
	public String getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	public Double getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(Double expectedPrice) {
		this.expectedPrice = expectedPrice;
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