package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;
import com.dsj.modules.system.enums.ServiceTypeEnums;

import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public class CompanyPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String companyName;		// 公司名
	private String shortName;		//公司名称缩写
	private String city;		// 公司所在城市
	private String ico;		// 公司logo
	private String introduce; //公司介绍
	private String serviceType; //业务类型
	private Integer companyType; //公司类型
	private Integer createPreson;		// 创建人
	private Integer updatePreson;		// 修改人
	private Date updateTime;		// 修改时间
	private String serviceTypeName;
	public String getServiceTypeName() {
		if(this.serviceType!=null&&!this.equals("")){
			String[] temp = this.serviceType.split(",");
			for(int i=0;i<temp.length;i++){
				serviceTypeName = serviceTypeName+","+ServiceTypeEnums.parse(Integer.valueOf(temp[i]));
			}
			serviceTypeName = serviceTypeName.substring(5);
		}
		
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private Integer deleteFlag;		// 删除标记
	
	public CompanyPo() {
		super();
	}

	public CompanyPo(Long id){
		this();
	}
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}
	
	public Integer getCreatePreson() {
		return createPreson;
	}

	public void setCreatePreson(Integer createPreson) {
		this.createPreson = createPreson;
	}
	
	public Integer getUpdatePreson() {
		return updatePreson;
	}

	public void setUpdatePreson(Integer updatePreson) {
		this.updatePreson = updatePreson;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}