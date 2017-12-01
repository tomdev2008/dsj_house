package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public class EvelopersPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long areaOneId;		// 一级地区id
	private Long areaTwoId;		// 二级地区id
	private Long areaThreeId;		// 三级地区id(暂时不用)
	private String name;		// 开发商名称
	private String companyLicenseNum;		// 企业营业执照号码
	private String companyLicensePhoto;		// 营业执照照片
	private String loupanName;		// 楼盘名称(多格式','分隔)
	private String promisePhoto;		// 承诺书照片
	private String operationName;		// 开发商操作人姓名
	private String operationPhone;		// 开发商操作人电话
	private String operationCard;		// 开发商操作人身份证号
	private Date updateTime;		// update_time
	private String avatarReUrl;    //头像 长方形
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public EvelopersPo() {
		super();
	}

	public EvelopersPo(Long id){
		this();
	}
	
	public Long getAreaOneId() {
		return areaOneId;
	}

	public void setAreaOneId(Long areaOneId) {
		this.areaOneId = areaOneId;
	}
	
	public Long getAreaTwoId() {
		return areaTwoId;
	}

	public void setAreaTwoId(Long areaTwoId) {
		this.areaTwoId = areaTwoId;
	}
	
	public Long getAreaThreeId() {
		return areaThreeId;
	}

	public void setAreaThreeId(Long areaThreeId) {
		this.areaThreeId = areaThreeId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompanyLicenseNum() {
		return companyLicenseNum;
	}

	public void setCompanyLicenseNum(String companyLicenseNum) {
		this.companyLicenseNum = companyLicenseNum;
	}
	
	public String getCompanyLicensePhoto() {
		return companyLicensePhoto;
	}

	public void setCompanyLicensePhoto(String companyLicensePhoto) {
		this.companyLicensePhoto = companyLicensePhoto;
	}
	
	public String getLoupanName() {
		return loupanName;
	}

	public void setLoupanName(String loupanName) {
		this.loupanName = loupanName;
	}
	
	public String getPromisePhoto() {
		return promisePhoto;
	}

	public void setPromisePhoto(String promisePhoto) {
		this.promisePhoto = promisePhoto;
	}
	
	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	public String getOperationPhone() {
		return operationPhone;
	}

	public void setOperationPhone(String operationPhone) {
		this.operationPhone = operationPhone;
	}
	
	public String getOperationCard() {
		return operationCard;
	}

	public void setOperationCard(String operationCard) {
		this.operationCard = operationCard;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAvatarReUrl() {
		return avatarReUrl;
	}

	public void setAvatarReUrl(String avatarReUrl) {
		this.avatarReUrl = avatarReUrl;
	}
	
}