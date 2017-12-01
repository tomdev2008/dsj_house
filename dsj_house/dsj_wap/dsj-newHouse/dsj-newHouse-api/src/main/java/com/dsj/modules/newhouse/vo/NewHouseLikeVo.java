package com.dsj.modules.newhouse.vo;

import java.io.Serializable;

public class NewHouseLikeVo  implements Serializable {
	
	private Long id;//楼盘id
	private String name;//楼盘名称
	private String imgUrl;//楼盘图片
	private Integer referencePrice;		// 参考单价
	private Integer totalPrice;//总价
	private Integer aroundPrice;//周边单价
	//先单价 总价 周边单价
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getReferencePrice() {
		return referencePrice;
	}
	public void setReferencePrice(Integer referencePrice) {
		this.referencePrice = referencePrice;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getAroundPrice() {
		return aroundPrice;
	}
	public void setAroundPrice(Integer aroundPrice) {
		this.aroundPrice = aroundPrice;
	}
	
}
