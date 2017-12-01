package com.dsj.modules.evaluate.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:52:42.
 * @版本: 1.0 .
 */
public class AgentInfoPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String agentName;		// 经纪人姓名
	private Long agentId;		// 经纪人ID
	private String cityName;		// 城市
	private Long cityCode;		// 城市编号
	private Integer totalScore;		// 累计分值
	private Integer baseScore;		// 基础分
	private Integer businessScore;		// 业务分
	private Integer educationScore;		// 学历分
	private Integer experienceScore;		// 从业年限分
	private Date updateTime;		// 修改时间
	private Integer updatePerson;		// 修改人
	private Integer createPerson;		// 创建人
	
	public AgentInfoPo() {
		super();
	}

	public AgentInfoPo(Long id){
		this();
	}
	

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Long getCityCode() {
		return cityCode;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}
	
	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
	public Integer getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(Integer baseScore) {
		this.baseScore = baseScore;
	}
	
	public Integer getBusinessScore() {
		return businessScore;
	}

	public void setBusinessScore(Integer businessScore) {
		this.businessScore = businessScore;
	}
	
	public Integer getEducationScore() {
		return educationScore;
	}

	public void setEducationScore(Integer educationScore) {
		this.educationScore = educationScore;
	}
	
	public Integer getExperienceScore() {
		return experienceScore;
	}

	public void setExperienceScore(Integer experienceScore) {
		this.experienceScore = experienceScore;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Integer updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}
	
}