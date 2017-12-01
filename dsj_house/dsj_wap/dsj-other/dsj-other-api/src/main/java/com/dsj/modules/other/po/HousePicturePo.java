package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.楼盘字典图片
 * @作者: kk.
 * @创建时间: 2017-07-16 08:34:39.
 * @版本: 1.0 .
 */
public class HousePicturePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer pictureType;		// 图片类型
	private Integer objType;		// 关联类型
	private String originObjId;		// 原id
	private String pictureUrl;		// 图片地址
	private Long objId;		// 关联源id
	private Integer isCover;		// 是否设置封面
	private Integer deleteFlag;		// delete_flag
	private Date updateTime;		// 更新时间
	private Integer createPerson;		// 创建人
	private String updatePerson;		// 更新人
	
	public HousePicturePo() {
		super();
	}

	public HousePicturePo(Long id){
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
	
	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	
}