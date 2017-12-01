package com.dsj.modules.evaluate.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:53:42.
 * @版本: 1.0 .
 */
public class AgentDailyScorePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String agentName;		// 经纪人姓名
	private Long agentId;		// 经纪人ID
	private String cityName;		// 城市
	private Long cityCode;		// 城市编号
	private Long itemId;		// 细项ID
	private String itemName;		// 细项
	private String updateDate;		// 日期
	private Integer score;		// 日得分
	private Integer count;		// 得分次数
	private Date updateTime;		// 修改时间
	private Integer updatePerson;		// 修改人
	private Integer createPerson;		// 创建人
	
	public AgentDailyScorePo() {
		super();
	}

	public AgentDailyScorePo(Long id){
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
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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