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
public class BusinessPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;		// 数据权限角色集合
	private String type;		// 是细类型本人：1，本部：2，全部：3
	
	public BusinessPo() {
		super();
	}

	public BusinessPo(Long id){
		this();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}