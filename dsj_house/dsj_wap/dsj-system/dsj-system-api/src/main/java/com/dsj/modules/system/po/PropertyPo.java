package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-21 17:37:37.
 * @版本: 1.0 .
 */
public class PropertyPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;		// dsj_user表id
	private String avatarReUrl;		// 头像
	private String avatarUrl;		// 头像正方形链接
	private String avatarMasterUrl;		// 头像原图
	private Integer company;		// 公司id
	private String companyName;			//公司名
	private String name;		// 姓名
	private String tellPhone;		// 电话号码
	private String province;		// 省
	private String city;		// 所在城市
	private String area;		// 行政区域
	private String business;		// 业务范围id
	private String businessName;		//业务范围名称
	private Integer auditStatus;		// 上下架状态 0:下架 1:上架
	private Integer updateUser;		// 更新人
	private Integer createUser;		// 创建人
	private Date updateTime;		// 更新时间
	private Integer sort;		// 排序字段
	private String sex;		// 性别
	private Integer deal;		// 接单数
	private Integer recommend;  //推荐
	private Date recommendTime; //推荐时间
	private String provinceName;
	private String cityName;
	private String areaName;
	
	public String getAvatarMasterUrl() {
		return avatarMasterUrl;
	}

	public void setAvatarMasterUrl(String avatarMasterUrl) {
		this.avatarMasterUrl = avatarMasterUrl;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public PropertyPo() {
		super();
	}

	public PropertyPo(Long id){
		this();
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getAvatarReUrl() {
		return avatarReUrl;
	}

	public void setAvatarReUrl(String avatarReUrl) {
		this.avatarReUrl = avatarReUrl;
	}
	
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTellPhone() {
		return tellPhone;
	}

	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	
	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Integer getDeal() {
		return deal;
	}

	public void setDeal(Integer deal) {
		this.deal = deal;
	}
	
}