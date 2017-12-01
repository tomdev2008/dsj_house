package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;

import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 19:11:19.
 * @版本: 1.0 .
 */
public class FlatUserPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;		// 用户账号ID
	private String flatName;		// 品牌公寓名称
	private String provinceCode;		// 所在省
	private String cityCode;		// 所在市
	private String businessLicence;		// 营业执照
	private String businessLicencePhoto;		// 营业执照照片
	private String undertaking;		// 承诺书
	private String contact;		// 联系人
	private String contactPhone;		// 联系电话
	private String contactIdCard;		// 联系人身份证号
	
	public FlatUserPo() {
		super();
	}

	public FlatUserPo(Long id){
		this();
	}
	
	public String getFlatName() {
		return flatName;
	}

	public void setFlatName(String flatName) {
		this.flatName = flatName;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getBusinessLicence() {
		return businessLicence;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}
	
	public String getUndertaking() {
		return undertaking;
	}

	public void setUndertaking(String undertaking) {
		this.undertaking = undertaking;
	}
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	public String getContactIdCard() {
		return contactIdCard;
	}

	public void setContactIdCard(String contactIdCard) {
		this.contactIdCard = contactIdCard;
	}
	
	public String getBusinessLicencePhoto() {
		return businessLicencePhoto;
	}

	public void setBusinessLicencePhoto(String businessLicencePhoto) {
		this.businessLicencePhoto = businessLicencePhoto;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}