package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:15.
 * @版本: 1.0 .
 */
public class HousePictureCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer pictureType;		// 图片类型  2户型图  4楼盘图  5配套图
	private Integer objType;		// 关联类型   2小区
	private String path;		// 本地路径
	private String originObjId;		// 原小区id
	private String originPictureUrl;		// 原图片地址
	private String pictureUrl;		// 图片地址
	private Integer isCover;		// 是否设置封面
	private Integer deleteFlag;		// delete_flag
	private Date updateTime;		// 更新时间
	private String updatePerson;		// 更新人
	private Long objId;		// old_house_lianjia_community 的id(合并以后做关联关系)
	private Integer compayType;//公司类型
	private Integer isUpload;		// 是否上传
	private Integer isDownload;		// 是否下载
	
	public HousePictureCrawlerPo() {
		super();
	}

	public HousePictureCrawlerPo(Long id){
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
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	
	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
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
		return "HousePictureCrawlerPo [pictureType=" + pictureType + ", objType=" + objType + ", path=" + path
				+ ", originObjId=" + originObjId + ", originPictureUrl=" + originPictureUrl + ", pictureUrl="
				+ pictureUrl + ", isCover=" + isCover + ", deleteFlag=" + deleteFlag + ", updateTime=" + updateTime
				+ ", updatePerson=" + updatePerson + ", objId=" + objId + ", isUpload=" + isUpload + ", isDownload="
				+ isDownload + "]";
	}

	
}