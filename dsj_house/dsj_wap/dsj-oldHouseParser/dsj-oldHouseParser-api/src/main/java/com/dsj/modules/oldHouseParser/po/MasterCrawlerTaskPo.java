package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-27 14:58:21.
 * @版本: 1.0 .
 */
public class MasterCrawlerTaskPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Date updateTime;		// update_time
	private String taskCode;		// 任务码  0000  (链家，我爱我家，麦田，中原)
	private Integer type;		// 任务类型
	
	public MasterCrawlerTaskPo() {
		super();
	}

	public MasterCrawlerTaskPo(Long id){
		this();
	}
	

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}