package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 16:19:36.
 * @版本: 1.0 .
 */
public class FwOrderDetailPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long orderId;		// order_id
	private String orderNo;		// order_no
	private Long productSkuId;		// 商品skuid
	private Long num;		// 购买数量
	private Double productPirce;		// 商品价格
	private String productName;		// 商品名称
	private Long typeId;		// 1
	private Long nodeId;		// 节点id
	private String nodeName;		// 节点名称
	private Integer authStatus;		// 审核状态 1待审核 2审核通过 3驳回
	private Long dbuserid ;
	
	public FwOrderDetailPo() {
		super();
	}

	public FwOrderDetailPo(Long id){
		this();
	}
	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public Long getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}
	
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	public Double getProductPirce() {
		return productPirce;
	}

	public void setProductPirce(Double productPirce) {
		this.productPirce = productPirce;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public Long getDbuserid() {
		return dbuserid;
	}

	public void setDbuserid(Long dbuserid) {
		this.dbuserid = dbuserid;
	}
	
}