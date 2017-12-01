package com.dsj.modules.other.vo;

import com.dsj.modules.other.po.HouseDirectoryPo;

public class HouseDirectoryFrontVo extends HouseDirectoryPo{
	
	private static final long serialVersionUID = 1L;
	
	private String statusName;//状态名称
	private String coordinate;//坐标拼接值
	
	private String certificateName;//产权性质
	
	private String wyTypeName;//物业类型
	
	private String achTypeName;//建筑类型

	private String heatingModeName;//供暖方式
	
	
	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getWyTypeName() {
		return wyTypeName;
	}

	public void setWyTypeName(String wyTypeName) {
		this.wyTypeName = wyTypeName;
	}

	public String getAchTypeName() {
		return achTypeName;
	}

	public void setAchTypeName(String achTypeName) {
		this.achTypeName = achTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getHeatingModeName() {
		return heatingModeName;
	}

	public void setHeatingModeName(String heatingModeName) {
		this.heatingModeName = heatingModeName;
	}
}
