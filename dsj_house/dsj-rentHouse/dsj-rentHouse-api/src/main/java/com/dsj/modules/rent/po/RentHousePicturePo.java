package com.dsj.modules.rent.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-10 11:40:37.
 * @版本: 1.0 .
 */
public class RentHousePicturePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer pictureType;		// 图片类型
	private Integer objType;		// 大类（品牌公寓，普通租房）
	private String originObjId;		// 原二手房id,原小区id
	private String pictureUrl;		// 图片地址
	private Long objId;		// 品牌公寓id,普通租房id
	private Integer isCover;		// 是否设置封面
	private Integer deleteFlag;		// delete_flag
	private Date updateTime;		// update_time
	private Integer createPerson;		// 创建人
	private Integer updatePerson;		// 更新人
	
	public RentHousePicturePo() {
		super();
	}

	public RentHousePicturePo(Long id){
		this();
	}
	

	public Integer getPictureType() {
		return pictureType;
	}

	public void setPictureType(Integer pictureType) {
		this.pictureType = pictureType;
	}
	
	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}
	
	public String getOriginObjId() {
		return originObjId;
	}

	public void setOriginObjId(String originObjId) {
		this.originObjId = originObjId;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}
	
	public Integer getIsCover() {
		return isCover;
	}

	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
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
	
}