package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:04.
 * @版本: 1.0 .
 */
public class OrderNodeJdPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long orderId;		// 订单id
	private Long orderDetailId;		// 订单详情id
	private Long nodeId;		// 节点id
	private String nodeName;		// 节点名称
	private String pcname;		// 节点名称
	private Integer isauth; //是否需要审核
	private Integer status;		// 1已结束 2未结束
	private Long dealUserId;		// deal_user_id
	private String dealUserName;		// deal_user_name
	private Date dealTime;		// deal_time
	private List<FwNodeFieldPo> fieldList;
	
	
	public List<FwNodeFieldPo> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FwNodeFieldPo> fieldList) {
		this.fieldList = fieldList;
	}

	public String getPcname() {
		return pcname;
	}

	public void setPcname(String pcname) {
		this.pcname = pcname;
	}

	public Integer getIsauth() {
		return isauth;
	}

	public void setIsauth(Integer isauth) {
		this.isauth = isauth;
	}

	public OrderNodeJdPo() {
		super();
	}

	public OrderNodeJdPo(Long id){
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
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getDealUserId() {
		return dealUserId;
	}

	public void setDealUserId(Long dealUserId) {
		this.dealUserId = dealUserId;
	}
	
	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}
	
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
}