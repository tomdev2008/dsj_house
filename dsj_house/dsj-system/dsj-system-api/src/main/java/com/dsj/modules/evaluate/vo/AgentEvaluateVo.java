package com.dsj.modules.evaluate.vo;

import com.dsj.modules.evaluate.po.AgentEvaluatePo;

public class AgentEvaluateVo extends AgentEvaluatePo {
	
	private String highRate;
	private String midRate;
	private String badRate;
	
	public String getHighRate() {
		return highRate;
	}
	
	public void setHighRate(String highRate) {
		this.highRate = highRate;
	}
	
	public String getMidRate() {
		return midRate;
	}
	
	public void setMidRate(String midRate) {
		this.midRate = midRate;
	}
	
	public String getBadRate() {
		return badRate;
	}
	
	public void setBadRate(String badRate) {
		this.badRate = badRate;
	}
	
}
