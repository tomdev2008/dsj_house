package com.dsj.modules.pagelayout.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 10:57:52.
 * @版本: 1.0 .
 */
public class WapItemSetPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String title;		// 标题
	private Integer type;		// 展示方式 1：大图+文字 2组图+文字3：小图+文字 4 纯文字
	private String url;		// 链接
	private String picture;		// 图片
	private String remark;		// 备注
	private Long labelId;		// 所属的标签
	private Integer sort;		// 顺序，1或者2
	private Integer status;		// 发布状态 1：未发布 2已发布
	
	public WapItemSetPo() {
		super();
	}

	public WapItemSetPo(Long id){
		this();
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}