package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 12:44:56.
 * @版本: 1.0 .
 */
public class FwTypeNodePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long typeId;		// 服务类型id
	private String name;		// 服务类型节点名称
	private String pcname;		// 服务类型节点名称
	private Integer nodeNum;		// 阶段步骤数字
	private Integer deleteFlag;		// delete_flag
	private Date updateTime;		// update_time
	private Integer isauth;
	private Long next;

	public Long getNext() {
		return next;
	}

	public void setNext(Long next) {
		this.next = next;
	}

	public Integer getIsauth() {
		return isauth;
	}

	public void setIsauth(Integer isauth) {
		this.isauth = isauth;
	}

	public FwTypeNodePo() {
		super();
	}

	public FwTypeNodePo(Long id){
		this();
	}
	

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(Integer nodeNum) {
		this.nodeNum = nodeNum;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPcname() {
		return pcname;
	}

	public void setPcname(String pcname) {
		this.pcname = pcname;
	}
	
}