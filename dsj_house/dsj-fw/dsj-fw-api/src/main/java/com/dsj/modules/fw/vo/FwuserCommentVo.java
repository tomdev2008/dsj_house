package com.dsj.modules.fw.vo;

import com.dsj.modules.fw.po.FwuserCommentPo;

public class FwuserCommentVo extends FwuserCommentPo {
		
	private String companyName;  //商家名
	private String name;		//代办人名
	private String areaName;		//行政区域
	private String spuName;			//商品名称
	private String business;	//业务范围
	private String businessName;
	private Integer deal;		//接单数
	private String tellPhone;	//代办人手机号
	private String phone;		//买家手机号
	private String orderNo;		//订单编号
	private Double orderPrice;		//订单金额
	private Double payment;			//支付金额
	private String userName;		
	private String realName;		//用户姓名
	private Integer reviewStatus;	//0未评论   1已评论
	private Integer count;		//获评次数
	private Integer orderDetailId;		//订单详情表id
	private String chaPingLv;
	private String zhongPingLv;
	private String haoPingLv;
	private Integer chaPingCount;
	private Integer zhongPingCount;
	private Integer haoPingCount;
	
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getChaPingCount() {
		return chaPingCount;
	}
	public void setChaPingCount(Integer chaPingCount) {
		this.chaPingCount = chaPingCount;
	}
	public Integer getZhongPingCount() {
		return zhongPingCount;
	}
	public void setZhongPingCount(Integer zhongPingCount) {
		this.zhongPingCount = zhongPingCount;
	}
	public Integer getHaoPingCount() {
		return haoPingCount;
	}
	public void setHaoPingCount(Integer haoPingCount) {
		this.haoPingCount = haoPingCount;
	}
	public String getChaPingLv() {
		return chaPingLv;
	}
	public void setChaPingLv(String chaPingLv) {
		this.chaPingLv = chaPingLv;
	}
	public String getZhongPingLv() {
		return zhongPingLv;
	}
	public void setZhongPingLv(String zhongPingLv) {
		this.zhongPingLv = zhongPingLv;
	}
	public String getHaoPingLv() {
		return haoPingLv;
	}
	public void setHaoPingLv(String haoPingLv) {
		this.haoPingLv = haoPingLv;
	}
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getSpuName() {
		return spuName;
	}
	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
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
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getTellPhone() {
		return tellPhone;
	}
	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public Integer getDeal() {
		return deal;
	}
	public void setDeal(Integer deal) {
		this.deal = deal;
	}
}
