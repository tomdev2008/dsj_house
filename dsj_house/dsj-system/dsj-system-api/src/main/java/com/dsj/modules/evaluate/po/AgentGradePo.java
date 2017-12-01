package com.dsj.modules.evaluate.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:50:55.
 * @版本: 1.0 .
 */
public class AgentGradePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;		// 等级名称
	private String gradeNo;		// 等级编号
	private Integer minScore;		// 最小分值
	private Integer maxScore;		// 最大分值
	private String smallIcon;		// 小图标
	private String bigIcon;		// 大图标
	private Date updateTime;		// 修改时间
	private Integer updatePerson;		// 修改人
	private Integer createPerson;		// 创建人
	
	public AgentGradePo() {
		super();
	}

	public AgentGradePo(Long id){
		this();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
	}
	
	public Integer getMinScore() {
		return minScore;
	}

	public void setMinScore(Integer minScore) {
		this.minScore = minScore;
	}
	
	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	
	public String getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}
	
	public String getBigIcon() {
		return bigIcon;
	}

	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
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