package com.dsj.modules.newhouse.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public class NewHouseDirectorySortPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer sortVal;		// 排序值
	private Long loupanId;		// 楼盘id
	private Date createTime;    
	private Date updateTime;		// update_time
	private Long createPerson;		// create_person
	private Long updatePerson;		// update_person
	private String loupanName;      //楼盘名称
	private String address;          //楼盘地址
	public NewHouseDirectorySortPo() {
		super();
	}

	public NewHouseDirectorySortPo(Long id){
		this();
	}
	

	public Integer getSortVal() {
		return sortVal;
	}

	public void setSortVal(Integer sortVal) {
		this.sortVal = sortVal;
	}
	
	public Long getLoupanId() {
		return loupanId;
	}

	public void setLoupanId(Long loupanId) {
		this.loupanId = loupanId;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Long getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Long createPerson) {
		this.createPerson = createPerson;
	}
	
	public Long getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Long updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getLoupanName() {
		return loupanName;
	}

	public void setLoupanName(String loupanName) {
		this.loupanName = loupanName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}