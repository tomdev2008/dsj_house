package com.dsj.modules.pagelayout.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-22 17:08:04.
 * @版本: 1.0 .
 */
public class WapLabelPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String name;		// 页签名称
	private String includeType;		// include_type
	private String weight;		// 权重
	private Integer updateUser;		// update_uesr
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public WapLabelPo() {
		super();
	}

	public WapLabelPo(Long id){
		this();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getIncludeType() {
		return includeType;
	}

	public void setIncludeType(String includeType) {
		this.includeType = includeType;
	}
	
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	
}