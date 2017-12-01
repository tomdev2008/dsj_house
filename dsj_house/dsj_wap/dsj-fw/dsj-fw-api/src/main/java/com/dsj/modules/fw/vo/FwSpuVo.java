package com.dsj.modules.fw.vo;

import com.dsj.modules.fw.po.FwSpuPo;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public class FwSpuVo extends FwSpuPo {
	
	private Double minPrice;		// 服务价格
	private Long skuId;//商品skuId
	private String describes;//服务描述
	private String guarantee;//服务保障
	

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	
	
}