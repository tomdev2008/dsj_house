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
public class PcNewHousePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long newHouseId;		// 楼盘ID
	private String newHouseName;		// 新房名称
	private String linkAddress;		// 链接地址
	private String picture;		// 图片
	private Long agentId;   //经纪人ID
	private Long labelId;   //标签ID
	private String recommend; //推荐语
	
	public PcNewHousePo() {
		super();
	}

	public PcNewHousePo(Long id){
		this();
	}
	

	public Long getNewHouseId() {
		return newHouseId;
	}

	public void setNewHouseId(Long newHouseId) {
		this.newHouseId = newHouseId;
	}
	
	public String getNewHouseName() {
		return newHouseName;
	}

	public void setNewHouseName(String newHouseName) {
		this.newHouseName = newHouseName;
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

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	
	
	
}