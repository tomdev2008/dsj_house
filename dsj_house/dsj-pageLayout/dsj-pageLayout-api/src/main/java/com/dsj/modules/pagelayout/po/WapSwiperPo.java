package com.dsj.modules.pagelayout.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-22 11:52:38.
 * @版本: 1.0 .
 */
public class WapSwiperPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String url;		// 链接地址
	private String imageUrl;		// 图片地址
	private Integer status;		// 状态，1：未发布 2：已发布
	private Date updateTime;		// update_time
	private Integer updateUser;		// update_user
	
	public WapSwiperPo() {
		super();
	}

	public WapSwiperPo(Long id){
		this();
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	
}