package com.dsj.modules.im.po;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: wangjl.
 * @创建时间: 2017-07-20 18:17:27.
 * @版本: 1.0 .
 */
public class IMDirectoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long houseId;		// 楼盘ID
	private Long agentId;		// 经纪人ID
	private Integer position;		// 位置
	private Integer isDuty;		// 是否楼盘维护人 0:否 1:是
	private Integer updatePerson;		// 修改人
	private Integer createPerson;		// 创建人
	
	public IMDirectoryPo() {
		super();
	}

	public IMDirectoryPo(Long id){
		this();
	}
	

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	public Integer getIsDuty() {
		return isDuty;
	}

	public void setIsDuty(Integer isDuty) {
		this.isDuty = isDuty;
	}
	
	public Integer getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Integer updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}
	
}