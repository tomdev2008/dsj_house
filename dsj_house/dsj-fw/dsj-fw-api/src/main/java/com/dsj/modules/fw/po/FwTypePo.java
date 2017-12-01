package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 12:44:56.
 * @版本: 1.0 .
 */
public class FwTypePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;		// 服务类型名称
	private Integer deleteFlag;		// 1删除 2未删除
	private Date updateTime;		// update_time
	
	public FwTypePo() {
		super();
	}

	public FwTypePo(Long id){
		this();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}