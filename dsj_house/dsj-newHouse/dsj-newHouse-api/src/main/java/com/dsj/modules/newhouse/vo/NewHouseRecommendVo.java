package com.dsj.modules.newhouse.vo;

import java.io.Serializable;

import com.dsj.common.entity.BaseEntity;

public class NewHouseRecommendVo extends BaseEntity{

	private Long newHouseId;//新房
	
	private Long recommendNewHouseId;//推荐新房id

	public Long getNewHouseId() {
		return newHouseId;
	}

	public void setNewHouseId(Long newHouseId) {
		this.newHouseId = newHouseId;
	}

	public Long getRecommendNewHouseId() {
		return recommendNewHouseId;
	}

	public void setRecommendNewHouseId(Long recommendNewHouseId) {
		this.recommendNewHouseId = recommendNewHouseId;
	}
	
	
}
