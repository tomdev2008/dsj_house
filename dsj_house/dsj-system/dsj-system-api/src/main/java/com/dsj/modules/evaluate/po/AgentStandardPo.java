package com.dsj.modules.evaluate.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:29:10.
 * @版本: 1.0 .
 */
public class AgentStandardPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String item;		// 细项
	private String paragraph;		// 项目
	private Integer paragraphNo;		// 项目编号
	private String section;		// 大类
	private Integer sectionNo;		// 大类编号
	private Integer count;		// 次数
	private Integer dailyScore;		// 日总分值
	private Integer score;		// 单次分值
	private Integer sort;     //序号
	private Date updateTime;		// 修改时间
	private Integer updatePerson;		// 修改人
	private Integer createPerson;		// 创建人
	
	public AgentStandardPo() {
		super();
	}

	public AgentStandardPo(Long id){
		this();
	}
	

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
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
	
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	public Integer getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(Integer sectionNo) {
		this.sectionNo = sectionNo;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getDailyScore() {
		return dailyScore;
	}

	public void setDailyScore(Integer dailyScore) {
		this.dailyScore = dailyScore;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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