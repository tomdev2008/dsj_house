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
public class NewHousePictureAuthHistoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer pictureType;		// 图片大类
	private String pictureTypeName;		// 图片大类名称
	private Integer pictureStatus;		// 图片细类(样板间...)
	private String pictureStatusName;		// 图片细类名称
	private String pictureName;		// 图片名称
	private String pictureUrl;		// 图片URL
	private Long objectId;		// 对象ID(楼盘ID)
	private Integer pictureFrist;		// 封面图  1是 2否
	private Long createPreson;		// 创建人
	private Long updatePreson;		// 修改人
	private Date createDate;		// 创建时间
	private Date updateDate;		// 修改时间
	private Integer deleteFlag;		// 1删除 2未删除
	private Integer order;		// 图片顺序
	private String title;		// 图片标题
	private String describes;		// 图片描述
	private String originObjectId;		// 原对象ID
	private String originPictureId;		// 原图片ID
	
	public NewHousePictureAuthHistoryPo() {
		super();
	}

	public NewHousePictureAuthHistoryPo(Long id){
		this();
	}
	

	public Integer getPictureType() {
		return pictureType;
	}

	public void setPictureType(Integer pictureType) {
		this.pictureType = pictureType;
	}
	
	public String getPictureTypeName() {
		return pictureTypeName;
	}

	public void setPictureTypeName(String pictureTypeName) {
		this.pictureTypeName = pictureTypeName;
	}
	
	public Integer getPictureStatus() {
		return pictureStatus;
	}

	public void setPictureStatus(Integer pictureStatus) {
		this.pictureStatus = pictureStatus;
	}
	
	public String getPictureStatusName() {
		return pictureStatusName;
	}

	public void setPictureStatusName(String pictureStatusName) {
		this.pictureStatusName = pictureStatusName;
	}
	
	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	public Integer getPictureFrist() {
		return pictureFrist;
	}

	public void setPictureFrist(Integer pictureFrist) {
		this.pictureFrist = pictureFrist;
	}
	
	public Long getCreatePreson() {
		return createPreson;
	}

	public void setCreatePreson(Long createPreson) {
		this.createPreson = createPreson;
	}
	
	public Long getUpdatePreson() {
		return updatePreson;
	}

	public void setUpdatePreson(Long updatePreson) {
		this.updatePreson = updatePreson;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	public String getOriginObjectId() {
		return originObjectId;
	}

	public void setOriginObjectId(String originObjectId) {
		this.originObjectId = originObjectId;
	}
	
	public String getOriginPictureId() {
		return originPictureId;
	}

	public void setOriginPictureId(String originPictureId) {
		this.originPictureId = originPictureId;
	}
	
}