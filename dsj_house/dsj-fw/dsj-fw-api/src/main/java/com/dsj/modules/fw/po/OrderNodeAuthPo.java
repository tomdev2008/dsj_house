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
public class OrderNodeAuthPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long orderId;		// order_id
	private Long orderDetailId;		// order_detail_id
	private Long nodeId;		// node_id
	private Integer status;		// 1提交 2驳回 3通过
	private String content;		// 备注
	private Long createPresonId;		// create_preson_id
	private String createPresonName;		// create_preson_name
	
	public OrderNodeAuthPo() {
		super();
	}

	public OrderNodeAuthPo(Long id){
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getCreatePresonId() {
		return createPresonId;
	}

	public void setCreatePresonId(Long createPresonId) {
		this.createPresonId = createPresonId;
	}
	
	public String getCreatePresonName() {
		return createPresonName;
	}

	public void setCreatePresonName(String createPresonName) {
		this.createPresonName = createPresonName;
	}
	
}