package com.dsj.data.web.utils;

import java.util.List;

public class PersonCenterVo {
	private List list;
	private long count;
	private int flag;
	
	private Long allOrderNum;// 全部订单
	private Long waitOrderNum; //待付款
	private Long finishOrderNum;//已付款
	private Long waitViewNum; //待评论
	private Long finishViewNum;//已评论
	private Long cancelNum ;//已取消
	private Long refundNum;//已退款
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Long getAllOrderNum() {
		return allOrderNum;
	}
	public void setAllOrderNum(Long allOrderNum) {
		this.allOrderNum = allOrderNum;
	}
	public Long getWaitOrderNum() {
		return waitOrderNum;
	}
	public void setWaitOrderNum(Long waitOrderNum) {
		this.waitOrderNum = waitOrderNum;
	}
	public Long getFinishOrderNum() {
		return finishOrderNum;
	}
	public void setFinishOrderNum(Long finishOrderNum) {
		this.finishOrderNum = finishOrderNum;
	}
	public Long getWaitViewNum() {
		return waitViewNum;
	}
	public void setWaitViewNum(Long waitViewNum) {
		this.waitViewNum = waitViewNum;
	}
	public Long getFinishViewNum() {
		return finishViewNum;
	}
	public void setFinishViewNum(Long finishViewNum) {
		this.finishViewNum = finishViewNum;
	}
	public Long getCancelNum() {
		return cancelNum;
	}
	public void setCancelNum(Long cancelNum) {
		this.cancelNum = cancelNum;
	}
	public Long getRefundNum() {
		return refundNum;
	}
	public void setRefundNum(Long refundNum) {
		this.refundNum = refundNum;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
