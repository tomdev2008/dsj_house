package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
public class NodeFieldPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long nodeId;		// 节点id
	private String fieldName;		// 字段名称
	private Integer type;
	private String fieldVal;
	
	
	
	public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public NodeFieldPo() {
		super();
	}

	public NodeFieldPo(Long id){
		this();
	}
	

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
}