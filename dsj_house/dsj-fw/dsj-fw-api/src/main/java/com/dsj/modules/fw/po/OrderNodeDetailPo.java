package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:04.
 * @版本: 1.0 .
 */
public class OrderNodeDetailPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long orderId;		// 订单id
	private Long orderDetailId;		// 订单详情id
	private Long nodeId;		// 节点id
	private Long fieldId;		// 字段id
	private String fieldName;		// 字段名称
	private String fieldVal;		// 字段值
	
	public OrderNodeDetailPo() {
		super();
	}

	public OrderNodeDetailPo(Long id){
		this();
	}
	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}
	
}