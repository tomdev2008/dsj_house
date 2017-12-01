package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-17 11:40:32.
 * @版本: 1.0 .
 */
public class AgentStatisticsPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;		// 经纪人userId
	private Date time;		// 统计的日期
	private Integer houseNewsNum;		// 经纪人发布楼盘动态
	private Integer agentNewsNum;		// 经纪人发布个人动态
	private Integer agentNewsReplyNum;		// 经纪人动态回复数
	private Integer agentNewsLikeNum;		// 经纪人动态好评数
	private Integer houseRemarkNum;		// 经纪人楼盘点评数
	private Integer houseRemarkReplyNum;		// 经纪人楼盘点评回复数
	private Integer houseRemarkLikeNum;		// 经纪人楼盘点评好评数
	private Integer agentGrade;		// 经纪人每日得分新增
	
	private String name;
	private String phone;
	private String companyName;
	
	private String timeString;
    
	private String timeStart;
	private String timeEnd;
	
	
	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);		
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public AgentStatisticsPo() {
		super();
	}

	public AgentStatisticsPo(Long id){
		this();
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public Integer getHouseNewsNum() {
		return houseNewsNum;
	}

	public void setHouseNewsNum(Integer houseNewsNum) {
		this.houseNewsNum = houseNewsNum;
	}
	
	public Integer getAgentNewsNum() {
		return agentNewsNum;
	}

	public void setAgentNewsNum(Integer agentNewsNum) {
		this.agentNewsNum = agentNewsNum;
	}
	
	public Integer getAgentNewsReplyNum() {
		return agentNewsReplyNum;
	}

	public void setAgentNewsReplyNum(Integer agentNewsReplyNum) {
		this.agentNewsReplyNum = agentNewsReplyNum;
	}
	
	public Integer getAgentNewsLikeNum() {
		return agentNewsLikeNum;
	}

	public void setAgentNewsLikeNum(Integer agentNewsLikeNum) {
		this.agentNewsLikeNum = agentNewsLikeNum;
	}
	
	public Integer getHouseRemarkNum() {
		return houseRemarkNum;
	}

	public void setHouseRemarkNum(Integer houseRemarkNum) {
		this.houseRemarkNum = houseRemarkNum;
	}
	
	public Integer getHouseRemarkReplyNum() {
		return houseRemarkReplyNum;
	}

	public void setHouseRemarkReplyNum(Integer houseRemarkReplyNum) {
		this.houseRemarkReplyNum = houseRemarkReplyNum;
	}
	
	public Integer getHouseRemarkLikeNum() {
		return houseRemarkLikeNum;
	}

	public void setHouseRemarkLikeNum(Integer houseRemarkLikeNum) {
		this.houseRemarkLikeNum = houseRemarkLikeNum;
	}
	
	public Integer getAgentGrade() {
		return agentGrade;
	}

	public void setAgentGrade(Integer agentGrade) {
		this.agentGrade = agentGrade;
	}
	
}