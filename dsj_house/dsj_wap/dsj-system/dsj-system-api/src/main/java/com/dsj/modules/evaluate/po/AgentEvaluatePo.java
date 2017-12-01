package com.dsj.modules.evaluate.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:31:17.
 * @版本: 1.0 .
 */
public class AgentEvaluatePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String agentName;		// 经纪人姓名
	private Long agentId;		// 经纪人ID
	private String cityName;		// 城市
	private Long cityCode;		// 城市编号
	private String paragraph;		// 项目
	private Integer paragraphNo;		// 项目编号
	private Integer highCount;		// 好评数
	private Integer midCount;		// 中评数
	private Integer badCount;		// 差评数
	private Integer totalCount;		// 总评论数
	private Date updateTime;		// 修改时间
	private Integer updatePerson;		// 修改人
	private Integer createPerson;		// 创建人
	
	public AgentEvaluatePo() {
		super();
	}

	public AgentEvaluatePo(Long id){
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
	
	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
	
	public Integer getParagraphNo() {
		return paragraphNo;
	}

	public void setParagraphNo(Integer paragraphNo) {
		this.paragraphNo = paragraphNo;
	}
	
	public Integer getHighCount() {
		return highCount;
	}

	public void setHighCount(Integer highCount) {
		this.highCount = highCount;
	}
	
	public Integer getMidCount() {
		return midCount;
	}

	public void setMidCount(Integer midCount) {
		this.midCount = midCount;
	}
	
	public Integer getBadCount() {
		return badCount;
	}

	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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