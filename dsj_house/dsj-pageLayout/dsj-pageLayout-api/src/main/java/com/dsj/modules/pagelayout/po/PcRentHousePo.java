package com.dsj.modules.pagelayout.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public class PcRentHousePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String linkAddress;		// 链接地址
	private String picture;		// 图片
	private Long objectId;       //房源ID
	private String rentName;
	
	public PcRentHousePo() {
		super();
	}

	public PcRentHousePo(Long id){
		this();
	}
	

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getRentName() {
		return rentName;
	}

	public void setRentName(String rentName) {
		this.rentName = rentName;
	}
	

	
}