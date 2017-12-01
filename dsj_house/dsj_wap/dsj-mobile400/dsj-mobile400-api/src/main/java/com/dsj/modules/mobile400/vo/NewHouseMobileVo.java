package com.dsj.modules.mobile400.vo;

import com.dsj.modules.mobile400.po.NewHouseMobilePo;

public class NewHouseMobileVo extends NewHouseMobilePo {
	private String callSuccessLv;		// 通话成功率
	private String callBusyLv;		// 通话未接通率
	private String callNotLv;		// 通话未拨通率
	private String time;
	private String newHouseName; //楼盘名称
	
	
	public String getNewHouseName() {
		return newHouseName;
	}
	public void setNewHouseName(String newHouseName) {
		this.newHouseName = newHouseName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCallSuccessLv() {
		return callSuccessLv;
	}
	public void setCallSuccessLv(String callSuccessLv) {
		this.callSuccessLv = callSuccessLv;
	}
	public String getCallBusyLv() {
		return callBusyLv;
	}
	public void setCallBusyLv(String callBusyLv) {
		this.callBusyLv = callBusyLv;
	}
	public String getCallNotLv() {
		return callNotLv;
	}
	public void setCallNotLv(String callNotLv) {
		this.callNotLv = callNotLv;
	}
	
}
