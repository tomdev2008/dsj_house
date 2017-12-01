package com.dsj.modules.system.vo;

import java.io.Serializable;

public class RecommendVo implements Serializable {
	private long houseId;
	private Integer type;
	public long getHouseId() {
		return houseId;
	}
	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
