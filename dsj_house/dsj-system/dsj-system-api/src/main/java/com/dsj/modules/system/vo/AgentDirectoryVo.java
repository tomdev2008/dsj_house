package com.dsj.modules.system.vo;

import com.dsj.common.entity.BaseEntity;

public class AgentDirectoryVo extends BaseEntity{
      /**
	 * 
	 */
	  private int agentId;
      private Long houseId;
      private int type;
      private Long newHouseId;
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public Long getNewHouseId() {
		return newHouseId;
	}
	public void setNewHouseId(Long newHouseId) {
		this.newHouseId = newHouseId;
	}
	
	
	
      
}
