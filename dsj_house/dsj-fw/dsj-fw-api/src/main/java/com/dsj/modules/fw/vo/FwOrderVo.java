package com.dsj.modules.fw.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.dsj.common.entity.BaseEntity;

public class FwOrderVo extends BaseEntity {

	private Long orderId;
	private String orderNo;
	private String requestid;
	private String areaName;
	private String productName;
	private BigDecimal payment;
	private String username;
	private Integer status;
	private Integer statusone;
	private String payurl;
	private String refundReason;
	private String nodeName;
	private Long nodeId;
	private Integer authStatus;
	private String phone;
	private String dbUserName;
	private String dbPhone;
	private String dbCompanyName;
	private String realName;
	private String areaCodeTwoName;
	private String areaCodeThreeName;
	private String mobile;
	private Integer reviewStatus;
	private Date reviewDate;
	private Date payTime;
	private Long nextNodeId;
	private String content;
	private Date refunddate;
	
	public Date getRefunddate() {
		return refunddate;
	}
	public void setRefunddate(Date refunddate) {
		this.refunddate = refunddate;
	}
	public Integer getStatusone() {
		return statusone;
	}
	public void setStatusone(Integer statusone) {
		this.statusone = statusone;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Long getNextNodeId() {
		return nextNodeId;
	}
	public void setNextNodeId(Long nextNodeId) {
		this.nextNodeId = nextNodeId;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public Integer getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAreaCodeTwoName() {
		return areaCodeTwoName;
	}
	public void setAreaCodeTwoName(String areaCodeTwoName) {
		this.areaCodeTwoName = areaCodeTwoName;
	}
	public String getAreaCodeThreeName() {
		return areaCodeThreeName;
	}
	public void setAreaCodeThreeName(String areaCodeThreeName) {
		this.areaCodeThreeName = areaCodeThreeName;
	}
	public String getDbUserName() {
		return dbUserName;
	}
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}
	public String getDbPhone() {
		return dbPhone;
	}
	public void setDbPhone(String dbPhone) {
		this.dbPhone = dbPhone;
	}
	public String getDbCompanyName() {
		return dbCompanyName;
	}
	public void setDbCompanyName(String dbCompanyName) {
		this.dbCompanyName = dbCompanyName;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	public String getPayurl() {
		return payurl;
	}
	public void setPayurl(String payurl) {
		this.payurl = payurl;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	
	
}
