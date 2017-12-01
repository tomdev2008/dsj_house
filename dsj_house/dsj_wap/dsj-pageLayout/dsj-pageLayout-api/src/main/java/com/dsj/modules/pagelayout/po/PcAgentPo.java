package com.dsj.modules.pagelayout.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public class PcAgentPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long agentId;		// 经纪人ID
	private String agentName;		// 经纪人名称
	private String comment;		// 点评内容
	private String linkAddress;		// 链接地址
	
	public PcAgentPo() {
		super();
	}

	public PcAgentPo(Long id){
		this();
	}
	

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	
}