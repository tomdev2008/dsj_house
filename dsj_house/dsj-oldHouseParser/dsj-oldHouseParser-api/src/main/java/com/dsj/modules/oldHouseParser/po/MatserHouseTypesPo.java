package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:53:47.
 * @版本: 1.0 .
 */
public class MatserHouseTypesPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;		// 名称
	private String area;		// 面积
	private String orientationsName;		// 朝向
	private String windTypeName;		// 仓户类型
	private String originHouseId;		// 源二手房id
	
	public MatserHouseTypesPo() {
		super();
	}

	public MatserHouseTypesPo(Long id){
		this();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getOrientationsName() {
		return orientationsName;
	}

	public void setOrientationsName(String orientationsName) {
		this.orientationsName = orientationsName;
	}
	
	public String getWindTypeName() {
		return windTypeName;
	}

	public void setWindTypeName(String windTypeName) {
		this.windTypeName = windTypeName;
	}
	
	public String getOriginHouseId() {
		return originHouseId;
	}

	public void setOriginHouseId(String originHouseId) {
		this.originHouseId = originHouseId;
	}
	
}