package com.dsj.modules.newhouse.po;

import com.dsj.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public class NewHouseOpenHandTimeAuthPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long houseId;		// 楼盘id
	private Integer type;		// 1开盘  2交房
	private Long wyType;		// 物业类型
	private String wyTypeName;  //物业类型名称
	private String describes;
	@JsonFormat(pattern="yyyy-MM-dd")// 开盘描述(XXX楼栋)
	private Date openHandTime;		// 开盘时间
	
	public String getWyTypeName() {
		return wyTypeName;
	}

	public void setWyTypeName(String wyTypeName) {
		this.wyTypeName = wyTypeName;
	}

	public NewHouseOpenHandTimeAuthPo() {
		super();
	}

	public NewHouseOpenHandTimeAuthPo(Long id){
		this();
	}
	

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getWyType() {
		return wyType;
	}

	public void setWyType(Long wyType) {
		this.wyType = wyType;
	}
	
	public Date getOpenHandTime() {
		return openHandTime;
	}

	public void setOpenHandTime(Date openHandTime) {
		this.openHandTime = openHandTime;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	
}