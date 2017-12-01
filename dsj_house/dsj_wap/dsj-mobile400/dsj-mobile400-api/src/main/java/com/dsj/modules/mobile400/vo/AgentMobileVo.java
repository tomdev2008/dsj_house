package com.dsj.modules.mobile400.vo;

import com.dsj.modules.mobile400.po.AgentMobilePo;

public class AgentMobileVo extends AgentMobilePo {
	private String callSuccessLv;		// 通话成功率
	private String callBusyLv;		// 通话未接通率
	private String callNotLv;		// 通话未拨通率
	private String time;
	private String agentName;
	private String tellPhone;
	private String startTime;
	private String endTime;
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getTellPhone() {
		return tellPhone;
	}
	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
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
