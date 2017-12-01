package com.dsj.modules.comment.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-14 11:47:28.
 * @版本: 1.0 .
 */
public class ClickHouseNewsPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer objType;		// 类别 楼盘动态1
	private Long objId;		// 关联id
	private Integer createUser;		// 创建人
	
	public ClickHouseNewsPo() {
		super();
	}

	public ClickHouseNewsPo(Long id){
		this();
	}
	

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}
	
	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
}