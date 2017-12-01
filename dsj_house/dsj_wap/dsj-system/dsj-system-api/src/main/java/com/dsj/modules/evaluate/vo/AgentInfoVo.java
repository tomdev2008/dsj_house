package com.dsj.modules.evaluate.vo;

import com.dsj.modules.evaluate.po.AgentInfoPo;

public class AgentInfoVo extends AgentInfoPo {
	
	private Long gradeId;
	private String gradeName;
	
	public Long getGradeId() {
		return gradeId;
	}
	
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	
	public String getGradeName() {
		return gradeName;
	}
	
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
}
