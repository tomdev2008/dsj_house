package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-29 13:46:52.
 * @版本: 1.0 .
 */
public class GroupTypePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String typegroupname;		// 字典名称
	private Long parentId;		// parent_id
	private Integer sort;		// 排序
	private Integer del;		// del
	
	public GroupTypePo() {
		super();
	}

	public GroupTypePo(Long id){
		this();
	}
	

	public String getTypegroupname() {
		return typegroupname;
	}

	public void setTypegroupname(String typegroupname) {
		this.typegroupname = typegroupname;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}