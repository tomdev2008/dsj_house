package com.dsj.modules.fw.vo;

import com.dsj.modules.fw.po.FwOrderDetailPo;

public class FwOrderDetailVo extends FwOrderDetailPo{
	
	private Integer propertyId;		//服务者id
	private Integer userId;		//用户id
	private Long spuId;		//服务id
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getSpuId() {
		return spuId;
	}
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
}
