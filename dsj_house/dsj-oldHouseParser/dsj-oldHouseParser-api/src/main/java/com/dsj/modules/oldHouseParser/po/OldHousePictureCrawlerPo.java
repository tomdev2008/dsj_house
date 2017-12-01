package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 15:27:30.
 * @版本: 1.0 .
 */
public class OldHousePictureCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer pictureType;		// 图片类型 1户型 2室内 3室外
	private String originObjId;		// 原二手房id
	private String originPictureUrl;		// 源图片url
	private String pictureUrl;		// 图片地址
	private String path;		// 本地路径
	private Long objId;		// 二手房id
	private Integer isCover;		// 是否设置封面
	private Integer deleteFlag;		// delete_flag
	private Integer compayType;//公司类型
	private Date updateTime;		// update_time
	private Integer isUpload;		// 是否上传
	private Integer isDownload;		// 是否下载
	
	public OldHousePictureCrawlerPo() {
		super();
	}

	public OldHousePictureCrawlerPo(Long id){
		this();
	}
	

	public Integer getPictureType() {
		return pictureType;
	}

	public void setPictureType(Integer pictureType) {
		this.pictureType = pictureType;
	}
	
	public String getOriginObjId() {
		return originObjId;
	}

	public void setOriginObjId(String originObjId) {
		this.originObjId = originObjId;
	}
	
	public String getOriginPictureUrl() {
		return originPictureUrl;
	}

	public void setOriginPictureUrl(String originPictureUrl) {
		this.originPictureUrl = originPictureUrl;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	
	public Integer getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(Integer isUpload) {
		this.isUpload = isUpload;
	}
	
	public Integer getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(Integer isDownload) {
		this.isDownload = isDownload;
	}

	public Integer getCompayType() {
		return compayType;
	}

	public void setCompayType(Integer compayType) {
		this.compayType = compayType;
	}

	@Override
	public String toString() {
		return "OldHousePictureCrawlerPo [pictureType=" + pictureType + ", originObjId=" + originObjId
				+ ", originPictureUrl=" + originPictureUrl + ", pictureUrl=" + pictureUrl + ", path=" + path
				+ ", objId=" + objId + ", isCover=" + isCover + ", deleteFlag=" + deleteFlag + ", compayType="
				+ compayType + ", updateTime=" + updateTime + ", isUpload=" + isUpload + ", isDownload=" + isDownload
				+ "]";
	}
	
}