package com.dsj.modules.newhouse.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public class NewHouseOpenHandTimeAuthHistoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long houseId;		// 楼盘id
	private Integer type;		// 1开盘  2交房
	private Long wyType;		// 物业类型
	private String wyTypeName;		// 物业类型名称
	private String describes;		// 开盘描述(XXX楼栋)
	private Date openHandTime;		// 开盘时间
	
	public NewHouseOpenHandTimeAuthHistoryPo() {
		super();
	}

	public NewHouseOpenHandTimeAuthHistoryPo(Long id){
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
	
	public String getWyTypeName() {
		return wyTypeName;
	}

	public void setWyTypeName(String wyTypeName) {
		this.wyTypeName = wyTypeName;
	}
	
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	public Date getOpenHandTime() {
		return openHandTime;
	}

	public void setOpenHandTime(Date openHandTime) {
		this.openHandTime = openHandTime;
	}
	
}