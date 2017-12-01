package com.dsj.modules.other.vo;

import com.dsj.modules.other.po.HouseDirectoryPo;

public class HouseDirectoryVo extends HouseDirectoryPo{
	
	private static final long serialVersionUID = 1L;
	
	private String statusName;//状态名称
	private String coordinate;//坐标拼接值
	private String heatingModeName;//供暖方式
	private String achTypeName; //建筑类型
	

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

	public String getAchTypeName() {
		return achTypeName;
	}

	public void setAchTypeName(String achTypeName) {
		this.achTypeName = achTypeName;
	}
}
