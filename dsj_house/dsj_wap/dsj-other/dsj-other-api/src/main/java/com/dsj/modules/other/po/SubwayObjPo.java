package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-13 17:55:12.
 * @版本: 1.0 .
 */
public class SubwayObjPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer objType;		// 关联类型
	private Long objId;		// 关联id
	private Integer subwayPid;		// 地铁线
	private Long subwayId;		// 地铁站
	private String distance;		// 距离
	private Integer deleteFlag;		// 删除标记
	private Integer createPerson;		// 创建人
	private Integer updatePerson;		// 更新人
	private Date updateTime;		// 更新时间
	
	public SubwayObjPo() {
		super();
	}

	public SubwayObjPo(Long id){
		this();
	}
	

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}
	
	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}
	
	public Integer getSubwayPid() {
		return subwayPid;
	}

	public void setSubwayPid(Integer subwayPid) {
		this.subwayPid = subwayPid;
	}
	
	public Long getSubwayId() {
		return subwayId;
	}

	public void setSubwayId(Long subwayId) {
		this.subwayId = subwayId;
	}
	
	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}
	
	public Integer getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Integer updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}