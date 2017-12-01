package com.dsj.modules.mobile400.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-15 15:08:50.
 * @版本: 1.0 .
 */
public class AgentMobilePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer agentCode;		// 经纪人id
	private Integer callCount;		// 通话总数
	private Integer callSuccess;		// 通话成功数
	private Integer callBusy;		// 通话未接通数
	private Integer callNot;		// 通话未拨通数
	private Date statTime;		// 统计时间
	
	public AgentMobilePo() {
		super();
	}

	public AgentMobilePo(Long id){
		this();
	}
	

	public Integer getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(Integer agentCode) {
		this.agentCode = agentCode;
	}
	
	public Integer getCallCount() {
		return callCount;
	}

	public void setCallCount(Integer callCount) {
		this.callCount = callCount;
	}
	
	public Integer getCallSuccess() {
		return callSuccess;
	}

	public void setCallSuccess(Integer callSuccess) {
		this.callSuccess = callSuccess;
	}
	
	public Integer getCallBusy() {
		return callBusy;
	}

	public void setCallBusy(Integer callBusy) {
		this.callBusy = callBusy;
	}
	
	public Integer getCallNot() {
		return callNot;
	}

	public void setCallNot(Integer callNot) {
		this.callNot = callNot;
	}
	
	public Date getStatTime() {
		return statTime;
	}

	public void setStatTime(Date statTime) {
		this.statTime = statTime;
	}
	
}