package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 16:19:35.
 * @版本: 1.0 .
 */
public class FwOrderPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String orderNo;		// 订单号
	private String payNo;		// 交易号
	private String requestid;		// 支付请求码
	private Integer sum;		// 购买数量
	private Integer status;		// 订单状态 1待支付，2取消订单 3支付完成
	private Double payment;		// 支付金额
	private Double orderPrice;		// 订单金额
	private Integer payType;		// 1支付宝
	private Date payTime;		// 支付时间
	private Date updateTime;		// 更新时间
	private Long userId;		// 用户id
	private Long fwuserId;		// 服务人id
	private String orderName;		// 订单名称
	private Date reviewDate;		// 0-未评论 1-已评论
	private String payurl;		// 1-删除	2-未删除
	private String refundReason;		// 退款原因
	private Integer refundtype;//退款类型
	private Date refunddate;//退款时间
	private Integer deleteFlag;		// 1-删除	2-未删除
	private String areaCodeOne;		// area_code_one
	private String areaCodeTwo;		// area_code_two
	private String areaCodeThree;		// area_code_three
	private String areaCodeOneName;		// area_code_one_name
	private String areaCodeTwoName;		// area_code_two_name
	private String areaCodeThreeName;		// area_code_three_name
	private Integer reviewStatus;
	private Integer statusone;  //状态1
	
	public Integer getStatusone() {
		return statusone;
	}

	public void setStatusone(Integer statusone) {
		this.statusone = statusone;
	}

	public Integer getRefundtype() {
		return refundtype;
	}

	public void setRefundtype(Integer refundtype) {
		this.refundtype = refundtype;
	}

	public Integer getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public FwOrderPo() {
		super();
	}

	public FwOrderPo(Long id){
		this();
	}
	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	
	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	public String getAreaCodeOne() {
		return areaCodeOne;
	}

	public void setAreaCodeOne(String areaCodeOne) {
		this.areaCodeOne = areaCodeOne;
	}
	
	public String getAreaCodeTwo() {
		return areaCodeTwo;
	}

	public void setAreaCodeTwo(String areaCodeTwo) {
		this.areaCodeTwo = areaCodeTwo;
	}
	
	public String getAreaCodeThree() {
		return areaCodeThree;
	}

	public void setAreaCodeThree(String areaCodeThree) {
		this.areaCodeThree = areaCodeThree;
	}
	
	public String getAreaCodeOneName() {
		return areaCodeOneName;
	}

	public void setAreaCodeOneName(String areaCodeOneName) {
		this.areaCodeOneName = areaCodeOneName;
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

	public Long getFwuserId() {
		return fwuserId;
	}

	public void setFwuserId(Long fwuserId) {
		this.fwuserId = fwuserId;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public Date getRefunddate() {
		return refunddate;
	}

	public void setRefunddate(Date refunddate) {
		this.refunddate = refunddate;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	
}