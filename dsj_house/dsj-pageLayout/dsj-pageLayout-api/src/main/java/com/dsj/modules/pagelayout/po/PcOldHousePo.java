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
public class PcOldHousePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String linkAddress;		// 链接地址
	private String picture;		// 图片
	private Long agentId;      //经纪人ID
	private String objectId;      //楼盘id
	private Long labelId;   //标签ID
	private String recommend; //推荐语
	private String oldHouseName;//楼盘名称
	public PcOldHousePo() {
		super();
	}

	public PcOldHousePo(Long id){
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

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
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

	public String getOldHouseName() {
		return oldHouseName;
	}

	public void setOldHouseName(String oldHouseName) {
		this.oldHouseName = oldHouseName;
	}

   
	
	
	
	
}